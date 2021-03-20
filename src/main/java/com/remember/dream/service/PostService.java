package com.remember.dream.service;

import com.remember.dream.domain.Post;
import com.remember.dream.dto.PostRequestDto;
import com.remember.dream.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private  final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(postRequestDto);
        return post.getId();
    }
}
