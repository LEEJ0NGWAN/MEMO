package com.example.restfulwebservice.post;

import com.example.restfulwebservice.post.service.PostService;
import com.example.restfulwebservice.user.User;
import com.example.restfulwebservice.user.exception.UserNotFoundException;
import com.example.restfulwebservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
public class PostController {

    @Autowired private UserService userService;
    @Autowired private PostService postService;

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long id) {

        Optional<User> user = Optional.of(userService.find(id));

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%d] not found",id));
        }

        return ResponseEntity.ok(user.get().getPosts());
    }

    @PostMapping("/posts")
    public ResponseEntity<?> create(@RequestBody Post p) {
        System.out.println(p.getUser());
        return ResponseEntity.ok(postService.create(p));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Post p) {

        if (!id.equals(p.getId()))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(postService.update(p));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        postService.delete(id);

        return ResponseEntity.ok().build();
    }
}
