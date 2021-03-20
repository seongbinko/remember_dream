package com.remember.dream.domain;

import com.remember.dream.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본 생성자 생성
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려준다.
public class Post  extends Timestamped{ // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Post(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.contents = content;
    }

    public Post(PostRequestDto postRequestDto) {
        this.username = postRequestDto.getUsername();
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
    }

    public void update(PostRequestDto postRequestDto) {
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();
    }

}
