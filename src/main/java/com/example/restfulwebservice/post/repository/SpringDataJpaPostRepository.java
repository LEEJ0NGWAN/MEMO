package com.example.restfulwebservice.post.repository;

import com.example.restfulwebservice.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository {

    @Override
    @Query("from Post p where p.id=:id")
    Optional<Post> findById(@Param("id") Long id);

    @Override
    List<Post> findByUserId(@Param("userId") Long userId);
}
