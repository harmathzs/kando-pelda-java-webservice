package com.example.demo;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepository {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> findById(Integer id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(String message) {
        int id = idGenerator.getAndIncrement();
        Post post = new Post(id, message, LocalDateTime.now());
        posts.put(id, post);
        return post;
    }

    public Optional<Post> updateMessage(Integer id, String newMessage) {
        Post post = posts.get(id);
        if (post != null) {
            post.setMessage(newMessage);
            return Optional.of(post);
        }
        return Optional.empty();
    }

    public boolean deleteById(Integer id) {
        return posts.remove(id) != null;
    }
}
