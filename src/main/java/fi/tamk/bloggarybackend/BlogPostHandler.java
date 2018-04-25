package fi.tamk.bloggarybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogPostHandler {

    //private List<BlogPost> blogPostList = new ArrayList<BlogPost>();
    @Autowired
    private final MyRepository blogPostList;

    BlogPostHandler(MyRepository blogPostList) {
        this.blogPostList = blogPostList;
    }

    public BlogPost saveEntity(BlogPost entity) {

        blogPostList.save(entity);
        return entity;
    }

    public Optional<BlogPost> delete(Long o) {

        Optional<BlogPost> toDelete = blogPostList.findById(o);

        if(toDelete != null) {

            blogPostList.deleteById(o);
            return toDelete;
        } else {

            return null;
        }
    }

    public Iterable findAll() {

        return blogPostList.findAll();
    }

    public Optional<BlogPost> findOne(Long o) {

        return blogPostList.findById(o);
    }

    public BlogPost update(BlogPost toUpdate) {

        BlogPost post = new BlogPost(toUpdate.getTitle(),
                toUpdate.getContent(),
                toUpdate.getPoster(),
                toUpdate.getId());

        blogPostList.save(post);
        return post;
    }
}