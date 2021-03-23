package com.remember.dream.repository;

import com.remember.dream.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true) // write를 사용하지 않아서 조금이라도 성능상에 이점을 가져오기 위함
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    boolean existsByTitle(String title);

    boolean existsByContents(String contents);

    Post findByTitle(String title);
}
