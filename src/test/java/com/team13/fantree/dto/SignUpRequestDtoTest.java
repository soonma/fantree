package com.team13.fantree.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignUpRequestDtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("테스트")
    void signupCheckValidTest() {
        //given
        SignUpRequestDto requestDto = new SignUpRequestDto();
        //when
        Set<ConstraintViolation<SignUpRequestDto>> result = validator.validate(requestDto);
        //then
        for (ConstraintViolation<SignUpRequestDto> violation : result) {
            assertNotNull(violation.getMessage());
            System.out.println("결과: " +violation.getMessage());
        }
    }
}