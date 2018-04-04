package fi.tamk.bloggarybackend;

import org.springframework.stereotype.Service;

@Service
public interface BlogPostRepository extends MyRepository<BlogPost, Long> {

    @Override
    default BlogPost saveEntity(BlogPost entity) {
        return null;
    }

    @Override
    default void delete(Long o) {

    }

    @Override
    default Iterable findAll() {
        return null;
    }

    @Override
    default BlogPost findOne(Long o) {
        return null;
    }
}
