package com.example.demo;

public class PostRequest {
    private String message;

    public PostRequest() {}

    public PostRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
