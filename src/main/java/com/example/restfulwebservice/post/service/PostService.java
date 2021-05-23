package com.example.restfulwebservice.post.service;

import com.example.restfulwebservice.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public Post create(Post p);
    public List<Post> searchByUserId(Long id);
    public Optional<Post> search(Long id);
    public Post update(Post p);
    public void delete(Long id);

}
