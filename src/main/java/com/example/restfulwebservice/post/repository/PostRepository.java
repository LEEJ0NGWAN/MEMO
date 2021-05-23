package com.example.restfulwebservice.post.repository;

import com.example.restfulwebservice.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post p);
    Optional<Post> findById(Long id);
    List<Post> findByUserId(Long userId);
    void deleteById(Long id);
}
