package com.team13.fantree.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String name;
    @Email
    private String email;
    private String headline;
}
