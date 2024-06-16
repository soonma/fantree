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

class CommentRequestDtoTest {
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("테스트")
    void vaildTest() {
        //given
        CommentRequestDto requestDto = new CommentRequestDto();
        //when
        Set<ConstraintViolation<CommentRequestDto>> result = validator.validate(requestDto);
        //then
        for (ConstraintViolation<CommentRequestDto> violation : result) {
            assertNotNull(violation.getMessage());
            System.out.println(violation.getMessage());
        }
    }


}