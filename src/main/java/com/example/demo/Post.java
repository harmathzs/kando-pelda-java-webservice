package com.example.demo;

import java.time.LocalDateTime;

public class Post {
    private Integer id;
    private String message;
    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Post() {}

    public Post(Integer id, String message, LocalDateTime createdDate) {
        setId(id);
        setMessage(message);
        setCreatedDate(createdDate);
    }
}
