# Filter-Application written with Spring Boot  </br>
The application is a for a technical interview task. </br>
The application is supposed to get data from a JSON file to so filter the response due to different needs. </br>

**Implementation steps:**
- [x] Simple Spring Boot Server Application Template
- [x] Fetching data from JSON with RestTemplate
- [x] Filtering posts by a search word sent in as a param
- [x] Encapsulation of hit-story with "<b>story</b>"
- [x] Present response as JSON-object
- [x] Find post by ID
- [X] Find where its author is mentioned and recursively find where respective authors are mentioned 


### Tech stack
* [Java stream API]( https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [RestTemplate](https://www.baeldung.com/rest-template)
* [Gson](https://github.com/google/gson)


### Set up
* In order to test the application you may run it first in an IDE you are comfortable with, ex Intellij.
* Use postman or browser to send requests to the application
    * http://localhost:8080/fetchPostsWhereMentioned?postId={put_id_here}
    * http://localhost:8080/filterBySearchWord?searchWord={put_search_word_here}
