package com.team13.fantree.dto;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private String password;
    private String newPassword;
    private String name;
    private String email;
    private String headline;
}
