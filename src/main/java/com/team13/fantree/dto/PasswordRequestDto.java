package com.team13.fantree.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordRequestDto {
    @NotBlank
    private String beforePassword;
    @NotBlank
    private String newPassword;
}

