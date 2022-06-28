package model;

import java.time.LocalDateTime;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int postCounter;
    private LocalDateTime registrationDate;

    public User(Integer id, String username, String email, String password, String firstname, String lastname, int postCounter, LocalDateTime registrationDate) {
        this(username, email, password, firstname, lastname, registrationDate);
        this.postCounter = postCounter;
        this.id = id;
    }

    public User(String username, String email, String password, String firstname, String lastname, LocalDateTime registrationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.registrationDate = registrationDate;
    }

    public User(String username, int postCounter) {
        this.username = username;
        this.postCounter = postCounter;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getLastname() {
        return lastname;
    }

    public int getPostCounter() {
        return postCounter;
    }
}
