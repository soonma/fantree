package com.team13.fantree.entity;

import com.team13.fantree.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CommentTest {
    private final CommentRepository commentRepository;

    @Autowired
    CommentTest(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    @DisplayName("엔티티 테스트 입니다")
    void entityCreateTest(){
        //given
        //Commnet 정보 삽입전 Entity
        User user = new User("fltnsah","1q2w3e4r","이순모","clsrn77990@naver.com","안녕하세요");
        Post post = new Post("게시글 내용",user);
        Comment comment = new Comment(post,user,"댓글내용");

        //when
        Comment saveComment = commentRepository.save(comment);

        //then
        assertEquals(saveComment.getUser().getName(),user.getName());
        assertNotNull(saveComment.getId());
    }

}