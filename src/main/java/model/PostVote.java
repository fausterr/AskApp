package model;

public class PostVote {
    private String username;
    private Integer postId;
    private String type;

    public PostVote(String username, Integer postId, String type) {
        this.username = username;
        this.postId = postId;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getType() {
        return type;
    }
}
