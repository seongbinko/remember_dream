package com.remember.dream.validator;

import com.remember.dream.dto.PostForm;
import com.remember.dream.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PostFormValidator implements Validator {

    private final PostRepository postRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(PostForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostForm postForm = (PostForm)target;
        if (postRepository.existsByTitle(postForm.getTitle())) {
            errors.rejectValue("title", "invalid.title", new Object[]{postForm.getTitle()}, "중복된 꿈 명이 존재합니다.");
        }
        if (postRepository.existsByContents(postForm.getContents())) {
            errors.rejectValue("contents", "invalid.contents", new Object[]{postForm.getContents()}, "중복된 꿈 내용이 존재합니다.");
        }
    }
}
