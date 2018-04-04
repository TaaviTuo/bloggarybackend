package fi.tamk.bloggarybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@RestController
public class MyRestController {

    @Autowired
    BlogPostHandler blogPostHandler;

    @PostConstruct
    public void init() {

        BlogPost kari = blogPostHandler.saveEntity(new BlogPost());
        BlogPost test = blogPostHandler.saveEntity(new BlogPost("Testiä", "Testitesti lul", new User()));
    }

    @RequestMapping(value = "/blogPosts")
    public Iterable getAll() {

        return blogPostHandler.findAll();
    }

    @RequestMapping(value = "/blogPosts/{userId}")
    public BlogPost getOne(@PathVariable int userId, Long id) {

        if (blogPostHandler.findOne(id) != null) {

            return blogPostHandler.findOne(id);
        } else {

            throw new MyBlogPostException(userId);
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Void> addBlogPost(@RequestBody BlogPost toAdd, UriComponentsBuilder b) {

        blogPostHandler.saveEntity(toAdd);

        UriComponents uriComponents = b.path("/blogPosts/{userId}").buildAndExpand(toAdd.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/delete")
    public void deleteBlogPost(@PathVariable int userId, Long toDelete) {

        if (blogPostHandler.findOne(toDelete) != null) {

            blogPostHandler.delete(toDelete);
        } else {

            throw new MyBlogPostException(userId);
        }
    }
}
