package fi.tamk.bloggarybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                "Testitesti lul", new User().toString()));
    }

    public MyRestController(){

    }

    //WORK IN PROGRESS
    @RequestMapping(path = "/adduser", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody User user, @RequestHeader HttpHeaders headers) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if (checkHeaders(headers)){
            //userRepository.save(user);
            return "User Saved";
        }
        return "User not Saved";
    }

    @RequestMapping(path = "/addpost", method = RequestMethod.POST) // Map ONLY POST Requests
    public ResponseEntity<BlogPost> update (@RequestParam String title, String content, String poster,
                                            @RequestHeader HttpHeaders headers) {
        // @RequestParam means it is a parameter from the GET or POST request
        BlogPost post = new BlogPost(title, content, poster);

        blogPostHandler.saveEntity(post);

        return new ResponseEntity<BlogPost>(post, HttpStatus.OK);

    }

    @RequestMapping(path = "/addcomment", method = RequestMethod.POST) // Map ONLY POST Requests
    public @ResponseBody BlogPost addNewComment (@RequestBody String comment, @RequestParam String user, @RequestHeader HttpHeaders headers) {
        // Change String in RequestBody to Comment when they work
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if (checkHeaders(headers)){
            //MessageThread tmp = messageThreadRepository.findOne(id);
            //tmp.getListOfMessages().add(msg);
            //messageThreadRepository.save(tmp);
            //msgRepository.save(m);
            return addNewComment(comment, user, headers);
        }
        return addNewComment(comment, user, headers);
    }

    @RequestMapping(path = "/blogposts/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<BlogPost> getPost(@PathVariable int userId, Long id) {
        id = Long.parseLong("" + userId);
        return blogPostHandler.findOne(id);
    }

    @GetMapping(path="/blogposts")
    public @ResponseBody Iterable<BlogPost> getAllPosts() {
        // This returns a JSON or XML with the users
        return blogPostHandler.findAll();
    }

    //@GetMapping(path="/allusers")
    //public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    //    return userRepository.findAll();
    //}

    //@RequestMapping(path = "/user", method = RequestMethod.GET)
    //public @ResponseBody User getUser(@RequestParam(value="id", defaultValue="") Long id) {
    //    return userRepository.findOne(id);
    //}

    @DeleteMapping(path = "/delete/{userId}")
    public @ResponseBody Optional<BlogPost> deletePost(@PathVariable int userId, Long id) {
        id = Long.parseLong("" + userId);
        return blogPostHandler.delete(id);
    }

    /*
    @PutMapping("update/{userid}")
    public ResponseEntity<BlogPost> updateArticle(@RequestBody Long id) {
        Optional<BlogPost> post = blogPostHandler.findOne(id);
        if (post != null) {
            blogPostHandler.update(id);
            return new ResponseEntity<BlogPost>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogPost>(post, HttpStatus.OK);
        }
    }*/

    public boolean checkHeaders(HttpHeaders headers){
        if (headers.containsKey("app_id")){
            if (headers.get("app_id").contains(HTTP_HEADER_ID)){
                return true;
            }
        }
        return false;
    }
}