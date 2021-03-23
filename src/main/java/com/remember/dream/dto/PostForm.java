package com.remember.dream.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PostForm {

    @NotBlank
    @Length(min = 3 , max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    private String username;

    @NotBlank
    @Length(min = 3, max = 20)
    private String title;

    @NotBlank
    @Length(min = 30, max = 200)
    private String contents;
}
