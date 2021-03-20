package com.remember.dream.controller;

import com.remember.dream.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @DisplayName("게시글 등록 테스트- 입력값 정상")
    @Test
    void createPost() throws Exception {
        mockMvc.perform(post("/api/dreams")
                .param("username", "seongbinko")
                .param("title", "title")
                .param("contents", "꿈을 꾸었다"))
                .andExpect(status().isOk());
    }

    @DisplayName("게시글 전체 조회 테스트- 입력값 정상")
    @Test
    void getPosts() throws Exception {
        mockMvc.perform(get("/api/dreams"))
                .andExpect(status().isOk()
        );
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    void deletePost() throws Exception {
        mockMvc.perform(delete("/api/dreams/{id}", "1"))
                .andExpect(status().isOk()).andReturn();

    }
}