package com.remember.dream.controller;

import com.remember.dream.domain.Post;
import com.remember.dream.dto.PostForm;
import com.remember.dream.repository.PostRepository;
import com.remember.dream.service.PostService;
import com.remember.dream.validator.PostFormValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostFormValidator postFormValidator;
    private final PostRepository postRepository;
    private final PostService postService;

    @InitBinder("postForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(postFormValidator);
    }

    @GetMapping("/")
    public String dreams() {
        return "dreams";
    }

    @GetMapping("/save-dream")
    public String saveDream(Model model) {
        model.addAttribute(new PostForm());
        return "post/save-dream";
    }


    @GetMapping("/api/dreams")
    @ResponseBody
    public List<Post> readPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/api/dreams")
    public String createPost(@Valid @ModelAttribute PostForm postForm, Errors errors) {
        if(errors.hasErrors()) {
           return "post/save-dream";
        }
        Post post = new Post(postForm);
        postRepository.save(post);

        return "redirect:/";
    }
    @GetMapping("/api/dreams/{id}")
    public String readPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("post",post.get());
        return "post/read-dream";
    }

    @PutMapping("/api/dreams/{id}")
    public String updateMemo(@PathVariable Long id, @Valid @ModelAttribute PostForm postForm, Errors errors, Model model) {

        if (errors.hasErrors()) {
            Optional<Post> originPost = postRepository.findById(id);
            String currentTitle = postForm.getTitle();
            String currentContents = postForm.getContents();

            originPost.get().setTitle(currentTitle);
            originPost.get().setContents(currentContents);

            model.addAttribute("post", originPost.get());

            List<FieldError> fieldErrors = errors.getFieldErrors();
            for(FieldError f : fieldErrors) {
                model.addAttribute(f.getField(), f.getDefaultMessage());
            }
            return "post/read-dream";
        }
        Post post = postService.update(id, postForm);
        model.addAttribute("post" , post);
        return "post/read-dream";
    }

    @DeleteMapping("/api/dreams/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }
}
