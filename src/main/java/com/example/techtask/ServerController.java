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

    @RequestMapping("/filterBySearchWord")
    @ResponseBody
    public String response(@RequestParam String word) {

        String url = "https://www.retriever-info.com/doccyexample/documents.json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = fetchData(url, restTemplate);
        String jsonResult = filterByStory(word, response.getBody());

        return jsonResult;
    }


    public ResponseEntity<String> fetchData(String url, RestTemplate restTemplate) {
        return restTemplate.getForEntity(url, String.class);
    }


    public String filterByStory(String word, String response) {

        Gson parser = new Gson();
        Hits hits = parser.fromJson(response, Hits.class);

        Stream<Document> documents = Arrays.stream(hits.getDocuments());
        List<Document> filteredDocs = documents.filter(doc -> doc.contains(word)).collect(Collectors.toList());
        filteredDocs.forEach(doc -> doc.encapsulate("b"));

        HashMap<String, Object> responseJson = new HashMap<String, Object>();

        responseJson.put("filteredBy ", word);
        responseJson.put("hitsNr", filteredDocs.size());
        responseJson.put("hits", filteredDocs);


        return parser.toJson(responseJson);
    }


    @RequestMapping("/findMentioned")
    @ResponseBody
    public String fetchPostIfAuthorMentioned(@RequestParam String postId) {

        String url = "https://www.retriever-info.com/doccyexample/documents.json";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = fetchData(url, restTemplate);

        Gson parser = new Gson();
        Hits hits = parser.fromJson(response.getBody(), Hits.class);

        ArrayList<Document> posts = new ArrayList<Document>();

        Document[] documents = hits.getDocuments();
        List<Document> filteredDocs = Arrays.stream(documents).filter(doc -> doc.compareId(postId)).collect(Collectors.toList());

        findWhereMentioned(posts, filteredDocs.get(0).getAuthor(), documents);

        HashMap<String, Object> responseJson = new HashMap<String, Object>();
        responseJson.put("startingPointId", postId);
        responseJson.put("posts", posts);

        return parser.toJson(responseJson);
    }

    public void findWhereMentioned(ArrayList<Document> posts, Author author, Document[] documents) {

        List<Document> postsWhereMentioned = Arrays.stream(documents).filter(doc -> doc.isMentioned(author.getId())).collect(Collectors.toList());

        if (postsWhereMentioned.size() == 0) {
            return;
        }

        postsWhereMentioned.stream().forEach(post -> posts.add(post));
        postsWhereMentioned.stream().forEach(post -> findWhereMentioned(posts, post.getAuthor(), documents));
    }

}
