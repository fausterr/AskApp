package model;

import java.time.LocalDateTime;

public class Vote {
    private Integer userId;
    private Integer postId;
    private Type type;
    private LocalDateTime dateAdded;

    public Vote(Integer userId, Integer postId, Type type, LocalDateTime dateAdded) {
        this.userId = userId;
        this.postId = postId;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public enum Type {
        UP, DOWN;
    }
}