package com.example.restfulwebservice.post.service;

import com.example.restfulwebservice.post.Post;
import com.example.restfulwebservice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    final private PostRepository postRepository;

    @Override
    public Post create(Post p) {
        return postRepository.save(p);
    }

    @Override
    public List<Post> searchByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Optional<Post> search(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post update(Post p) {
        return postRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
