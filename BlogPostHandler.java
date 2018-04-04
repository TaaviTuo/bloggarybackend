package fi.tamk.bloggarybackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostHandler implements BlogPostRepository {

    private List<BlogPost> blogPostList = new ArrayList<BlogPost>();

    @Override
    public BlogPost saveEntity(BlogPost entity) {

        blogPostList.add(entity);
        return entity;
    }

    @Override
    public void delete(Long o) {

        for(BlogPost c : blogPostList) {
            if(c.getId() == o) {
                blogPostList.remove(c);
            }
        }
    }

    @Override
    public Iterable findAll() {

        return blogPostList;
    }

    @Override
    public BlogPost findOne(Long o) {

        for(BlogPost c : blogPostList) {
            if(c.getId() == o) {
                return c;
            }
        }
        return null;
    }
}
