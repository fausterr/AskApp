package model;

public class FavouritePost {

    private int id;
    private Integer userId;
    private Integer postId;
    private String username;

    public FavouritePost(Integer userId, Integer postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public FavouritePost(Integer postId, String username) {
        this.postId = postId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }
}