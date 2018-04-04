package fi.tamk.bloggarybackend;

public class MyBlogPostException extends IllegalArgumentException {

    private int customerId;

    public MyBlogPostException(int id) {

        customerId = id;
    }

    public  int getCustomerId() {

        return customerId;
    }
}