package com.team13.fantree.entity;

import com.team13.fantree.repository.LikeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class LikeTest {
    private final LikeRepository likeRepository;

    @Autowired
    LikeTest(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;

    }

    @Test
    @DisplayName("좋아요 테스트")
    void likeTest() {
        //given
        Like like = new Like(1,1,"comment");
        //when
        Like likeSave = likeRepository.save(like);
        //then
        assertEquals(likeSave.getContentType(),ContentEnumType.COMMENT);
    }
}