package com.team13.fantree.entity;

import com.team13.fantree.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTest {

    @Autowired
    private final UserRepository userRepository;

    UserTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @DisplayName("User 정보 테스트")
    @Test
    void Test(){
        //given
        User user =new User("fltnsah","1q2w3e4r","이순모","clsrn77990@naver.com","안녕하세요");
        //when
        User userSave = userRepository.save(user);
        //then
        assertNotNull(userSave.getId());
    }

}