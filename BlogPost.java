package fi.tamk.bloggarybackend;

import java.util.Date;

public class BlogPost {

    private String header;
    private String content;
    private User poster;
    private Date datePosted;
    private int likes;
    private long id;
    private static long numOfPosts;

    public BlogPost() {

        this.header = "Everything sucks";
        this.content = "Yadayada. Yadayada.";
        this.poster = new User();
        setDatePosted();
        this.likes = 123;
        this.id = numOfPosts;
        numOfPosts++;
    }

    public BlogPost(String header, String content, User poster) {

        setHeader(header);
        setContent(content);
        setPoster(poster);
        setDatePosted();
        this.likes = 1;
        setId();
        numOfPosts++;
    }

    public void setId() {
        this.id = numOfPosts;
    }

    public long getId() {
        return id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public User getPoster() {
        return poster;
    }

    public void setDatePosted() {
        this.datePosted = new Date();
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }
}
