package com.example.techtask;

import com.example.techtask.DataStructures.Author;
import com.example.techtask.DataStructures.Document;
import com.example.techtask.DataStructures.Hits;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class ServerController {

    String url = "https://www.retriever-info.com/doccyexample/documents.json";
    RestTemplate restTemplate = new RestTemplate();
    Gson parser = new Gson();


    /**
     * TASKS 1-4
     **/
    @RequestMapping("/filterBySearchWord")
    @ResponseBody
    public String filterBySearchWord(@RequestParam String searchWord) {

        ResponseEntity<String> hitsJson = fetchData(url, restTemplate);

        List<Document> filteredHits = filterByStory(searchWord, hitsJson.getBody(), parser);

        if(filteredHits.size() == 0){
            return "No data for search word "+ searchWord +" found";
        }

        encapsulate(filteredHits, "b");

        HashMap<String, Object> response = packResponseStories(searchWord, filteredHits);

        return parser.toJson(response);
    }

    /**
     * BONUS TASK
     **/
    @RequestMapping("/fetchPostsWhereMentioned")
    @ResponseBody
    public String fetchPostsWhereMentioned(@RequestParam String postId) {

        ResponseEntity<String> hitsJson = fetchData(url, restTemplate);

        Hits hits = parser.fromJson(hitsJson.getBody(), Hits.class);

        ArrayList<Document> posts = new ArrayList<Document>();

        List<Document> filteredHits = Arrays.stream(hits.getDocuments())
                .filter(doc -> doc.compareId(postId))
                .collect(Collectors.toList());

        if(filteredHits.size() == 0){
            return "No data for post "+ postId +" found";
        }

        findWhereMentioned(posts, filteredHits.get(0).getAuthor(), hits.getDocuments());

        HashMap<String, Object> response = packResponseAuthorsMentioned(postId, posts);

        return parser.toJson(response);
    }

    /**Help functions**/
    public HashMap<String, Object> packResponseAuthorsMentioned(String postId, ArrayList<Document> posts){

        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("startingPointId", postId);
        response.put("posts", posts);

        return response;
    }

    public ResponseEntity<String> fetchData(String url, RestTemplate restTemplate) {
        return restTemplate.getForEntity(url, String.class);
    }


    public List<Document> filterByStory(String word, String response, Gson parser) {

        Hits hits = parser.fromJson(response, Hits.class);

        Stream<Document> documents = Arrays.stream(hits.getDocuments());
        List<Document> filteredHits = documents.filter(doc -> doc.contains(word)).collect(Collectors.toList());

        return filteredHits;
    }

    public void encapsulate(List<Document> hits, String tag) {
        hits.forEach(hit -> hit.encapsulate(tag));
    }

    public HashMap<String, Object> packResponseStories(String word, List<Document> hits) {

        HashMap<String, Object> response = new HashMap<String, Object>();

        response.put("filteredBy ", word);
        response.put("hitsNr", hits.size());
        response.put("hits", hits);

        return response;

    }

    public void findWhereMentioned(ArrayList<Document> posts, Author author, Document[] documents) {

        List<Document> postsWhereMentioned = Arrays.stream(documents)
                .filter(doc -> doc.isMentioned(author.getId()))
                .collect(Collectors.toList());

        if (postsWhereMentioned.size() == 0) {
            return;
        }

        postsWhereMentioned.stream().forEach(post -> posts.add(post));
        postsWhereMentioned.stream().forEach(post -> findWhereMentioned(posts, post.getAuthor(), documents));
    }


}
