package fi.tamk.bloggarybackend;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table (name="blogpost")
public class BlogPost {

    private String title;
    private String content;
    private String poster;
    private Date datePosted;
    @Id
    @GeneratedValue
    private Long id;
    private static long numOfPosts;

    public BlogPost() {

        this.title = "Everything sucks";
        this.content = "Yadayada.";
        this.poster = "Anonymous";
        setDatePosted();
        this.id = numOfPosts;
        numOfPosts++;
    }

    public BlogPost(String title, String content, String poster) {

        setTitle(title);
        setContent(content);
        setPoster(poster);
        setDatePosted();
        setId();
        numOfPosts++;
    }

    public BlogPost(String title, String content, String poster, Long id) {

        setTitle(title);
        setContent(content);
        setPoster(poster);
        setDatePosted();
        this.id = id;
    }

    public void setId() {
        this.id = numOfPosts;
    }

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setDatePosted() {
        this.datePosted = new Date();
    }

    public Date getDatePosted() {
        return datePosted;
    }
}