package com.remember.dream.domain;

import com.remember.dream.dto.PostForm;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // 테이블과 연계됨을 스프링에게 알려준다.
@Getter
@Setter
//@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post { // 생성,수정 시간을 자동으로 만들어줍니다.

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Post(PostForm postForm) {
        this.username = postForm.getUsername();
        this.title = postForm.getTitle();
        this.contents = postForm.getContents();
    }

    public void update(PostForm postForm) {
        this.title = postForm.getTitle();
        this.contents = postForm.getContents();
    }

}
