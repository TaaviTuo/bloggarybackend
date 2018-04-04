package fi.tamk.bloggarybackend;

import java.util.Date;

public class BlogPost {

    private String header;
    private String content;
    private User poster;
    private Date datePosted;
    private int likes;

    public BlogPost() {

        this.header = "Everything sucks";
        this.content = "Yadayada. Yadayada.";
        this.poster = new User();
        setDatePosted();
        this.likes = 123;
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
