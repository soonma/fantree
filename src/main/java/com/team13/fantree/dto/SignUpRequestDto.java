package com.team13.fantree.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank
    @Size(min = 10, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$",
        message = "ID 형식이 올바르지 않습니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{10,}$",
            message = "대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함, 최소 10글자 이상")
    private String password;

    private String name;

    @Email
    @NotNull
    private String email;

    private String headline;
}
