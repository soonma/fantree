package com.team13.fantree.entity;

import com.team13.fantree.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PostTest {
    private final PostRepository postRepository;

    @Autowired
    PostTest(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Test
    @DisplayName("Post 관련 테스트")
    void PostCreateTest(){
        //given
        User user = new User("fltnsah","1q2w3e4r","이순모","clsrn77990@naver.com","안녕하세요");
        Post post = new Post("게시글 내용",user);

        //when
        Post postSave = postRepository.save(post);
        //then
        assertEquals(postSave.getUser().getId(),user.getId());
    }


}