package model;


import java.time.LocalDateTime;

public class Comment {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String commentText;
    private LocalDateTime dateAdded;
    private String username;

    public Comment(Integer userId, Integer postId, String commentText, LocalDateTime dateAdded) {
        this.userId = userId;
        this.postId = postId;
        this.commentText = commentText;
        this.dateAdded = dateAdded;
    }

    public Comment(Integer postId, String commentText, String username) {
        this.postId = postId;
        this.commentText = commentText;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getUsername() {
        return username;
    }
}