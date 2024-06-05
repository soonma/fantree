package com.team13.fantree.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PasswordRequestDto {
    @NotBlank
    private String beforePassword;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{10,}$", message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함, 최소 10글자 이상")
    private String newPassword;
}
