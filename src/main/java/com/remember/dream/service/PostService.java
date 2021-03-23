package com.remember.dream.service;

import com.remember.dream.domain.Post;
import com.remember.dream.dto.PostForm;
import com.remember.dream.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private  final PostRepository postRepository;

    @Transactional
    public Post update(Long id, PostForm postForm) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(postForm);
        return post;
    }
}
