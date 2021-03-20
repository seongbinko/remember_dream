package com.remember.dream.controller;

import com.remember.dream.domain.Post;
import com.remember.dream.dto.PostRequestDto;
import com.remember.dream.repository.PostRepository;
import com.remember.dream.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/dreams")
    public List<Post> readPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/dreams")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        return postRepository.save(post);
    }
    @GetMapping("/api/dreams/{id}")
    public Optional<Post> readPost(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PutMapping("/api/dreams/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        postService.update(id, postRequestDto);
        return id;
    }

    @DeleteMapping("/api/dreams/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

}
