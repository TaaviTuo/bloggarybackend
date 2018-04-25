package fi.tamk.bloggarybackend;

import org.springframework.data.repository.CrudRepository;

public interface MyRepository extends CrudRepository<BlogPost, Long>{

}