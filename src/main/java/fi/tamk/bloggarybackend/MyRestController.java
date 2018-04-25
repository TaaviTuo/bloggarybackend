package fi.tamk.bloggarybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
public class MyRestController {

    private final String HTTP_HEADER_ID = "1";

    @Autowired
    BlogPostHandler blogPostHandler;

    @PostConstruct
    public void init() {

        BlogPost kari = blogPostHandler.saveEntity(new BlogPost());
        BlogPost test = blogPostHandler.saveEntity(new BlogPost("Testiä",
                "Testitesti lul", "Kari"));
    }

    public MyRestController(){

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/addpost", method = RequestMethod.POST)
    public ResponseEntity<BlogPost> addPost(@RequestBody BlogPost post, UriComponentsBuilder builder) {

        blogPostHandler.saveEntity(post);

        UriComponents uriComponents =
                builder.path("/{id}").buildAndExpand(post.getId());
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/blogposts/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<BlogPost> getPost(@PathVariable int userId, Long id) {
        id = Long.parseLong("" + userId);
        return blogPostHandler.findOne(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="/blogposts")
    public @ResponseBody Iterable<BlogPost> getAllPosts() {
        // This returns a JSON or XML with the users
        return blogPostHandler.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/delete/{userId}")
    public @ResponseBody Optional<BlogPost> deletePost(@PathVariable int userId, Long id) {
        id = Long.parseLong("" + userId);
        return blogPostHandler.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update/{id}")
    public @ResponseBody BlogPost update(@PathVariable String id,
                                         @RequestParam String title, String content, String poster){
        Long blogId = Long.parseLong(id);
        BlogPost toUpdate = new BlogPost(title, content, poster, blogId);

        return blogPostHandler.update(toUpdate);
    }

    public boolean checkHeaders(HttpHeaders headers){
        if (headers.containsKey("app_id")){
            if (headers.get("app_id").contains(HTTP_HEADER_ID)){
                return true;
            }
        }
        return false;
    }
}