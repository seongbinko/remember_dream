package com.remember.dream.controller;

import com.remember.dream.domain.Post;
import com.remember.dream.dto.PostForm;
import com.remember.dream.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void beforeEach() {
        PostForm postForm = new PostForm();
        postForm.setUsername("관리자");
        postForm.setTitle("테스트 제목");
        postForm.setContents("꿈을 꾼 내용을 알려드리자면 하루가 꼬박지나도 길 것 같습니다 앞으로도 자주 자주 꿈을 꿀 때 마다 등록해볼게요");
        postRepository.save(new Post(postForm));
    }

    @AfterEach
    void afterEach() {
        postRepository.deleteAll();
    }

    @DisplayName("메인 화면 보이는지 테스트")
    @Test
    void dreams() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dreams"));
    }

    @DisplayName("꿈 작성 화면 보이는지 테스트")
    @Test
    void saveDream() throws Exception {
        mockMvc.perform(get("/save-dream"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/save-dream"))
                .andExpect(model().attributeExists("postForm"));
    }

    @DisplayName("게시글 등록 테스트- 입력값 오류")
    @Test
    void createPost_with_wrong_input() throws Exception {
        mockMvc.perform(post("/api/dreams")
                .param("username", "***")
                .param("title", "꿈을 꾼 내용을 알려드립니다.")
                .param("contents", "12345")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("post/save-dream"));

    }

    @DisplayName("게시글 등록 테스트- 입력값 정상")
    @Test
    void createPost_with_correct_input() throws Exception {
        mockMvc.perform(post("/api/dreams")
                .param("username", "seongbinko")
                .param("title", "꿈을 꾼 내용을 알려드립니다.")
                .param("contents", "꿈을 꾼 내용을 알려드립니다. 제가 꿈을 꾼 내용은 사람들이 많은 곳에서 춤을 추고 있는 꿈입니다. 설레서 잠이 안오네요")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        assertTrue(postRepository.existsByTitle("꿈을 꾼 내용을 알려드립니다."));
        assertTrue(postRepository.existsByContents("꿈을 꾼 내용을 알려드립니다. 제가 꿈을 꾼 내용은 사람들이 많은 곳에서 춤을 추고 있는 꿈입니다. 설레서 잠이 안오네요"));
    }

    @DisplayName("게시글 전체 조회 테스트- 입력값 정상")
    @Test
    void readPosts() throws Exception {
        mockMvc.perform(get("/api/dreams"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk());
    }

    @DisplayName("게시글 상세 조회 테스트")
    @Test
    void readPost() throws Exception {
        Post post = postRepository.findByTitle("테스트 제목");

        mockMvc.perform(get("/api/dreams/{id}",post.getId())
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("post/read-dream"))
                .andExpect(model().attributeExists("post"));
    }
    @DisplayName("게시글 수정 테스트 - 입력값 오류")
    @Test
    void updatePost_wrong_input() throws Exception {
        Post post = postRepository.findByTitle("테스트 제목");

        mockMvc.perform(put("/api/dreams/{id}",post.getId())
                .param("title", "제목")
                .param("contents", "테스트입니다")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("post/read-dream"))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attributeExists("contents"));
        assertFalse(postRepository.existsByTitle("제목"));
        assertFalse(postRepository.existsByContents("테스트입니다"));
    }

    @DisplayName("게시글 수정 테스트 - 입력값 정상")
    @Test
    void updatePost_correct_input() throws Exception {
        Post post = postRepository.findByTitle("테스트 제목");

        mockMvc.perform(put("/api/dreams/{id}",post.getId())
                .param("username", post.getUsername())
                .param("title", "제목이 수정 되는지 테스트입니다")
                .param("contents", "테스트입니다 꿈을 꾼 내용을 알려드립니다. 제가 꿈을 꾼 내용은 사람들이 많은 곳에서 춤을 추고 있는 꿈입니다. 설레서 잠이 안오네요")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("post/read-dream"))
                .andExpect(model().attributeExists("post"));
        assertTrue(postRepository.existsByTitle("제목이 수정 되는지 테스트입니다"));
        assertTrue(postRepository.existsByContents("테스트입니다 꿈을 꾼 내용을 알려드립니다. 제가 꿈을 꾼 내용은 사람들이 많은 곳에서 춤을 추고 있는 꿈입니다. 설레서 잠이 안오네요"));
    }


    @DisplayName("게시글 삭제 테스트")
    @Test
    void deletePost() throws Exception {
        Post post = postRepository.findByTitle("테스트 제목");

        mockMvc.perform(delete("/api/dreams/{id}", post.getId())
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}