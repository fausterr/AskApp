package model;

import java.time.LocalDateTime;

public class Post {

    private Integer id;
    private String title;
    private String contents;
    private LocalDateTime dateAdded;
    private Integer categoryId;
    private Integer userId;
    private int voteCount;
    private String author;

    public Post(String title, String contents, LocalDateTime dateAdded, Integer categoryId, Integer userId) {
        this.title = title;
        this.contents = contents;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Post(Integer id, String title, String contents, LocalDateTime dateAdded, Integer categoryId, Integer userId) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Post(Integer id, String title, String contents, LocalDateTime dateAdded, int voteCount, String author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.dateAdded = dateAdded;
        this.voteCount = voteCount;
        this.author = author;
    }

    public Post(String title, String contents, Integer categoryId, String author) {
        this.title = title;
        this.contents = contents;
        this.categoryId = categoryId;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getAuthor() {
        return author;
    }
}
