package fi.tamk.bloggarybackend;

public class User {

    private String username;
    private String password;
    private int totalLikes;

    public User() {

        this.username = "anonymous";
        this.password = "asd123";
        this.totalLikes = 1323;
    }

    public User(String username, String password) {

        setUsername(username);
        setPassword(password);
        setTotalLikes();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTotalLikes() {
        this.totalLikes = totalLikes;
    }

    public int getTotalLikes() {
        return totalLikes;
    }
}
