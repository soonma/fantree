package com.team13.fantree.entity;


import com.team13.fantree.dto.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String headline;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    private String refreshToken;

    private String statusUpdate;


    public User(SignUpRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.headline = requestDto.getHeadline();
        this.status = UserStatusEnum.USER;
    }


    public void withDraw(){
        this.status = UserStatusEnum.NON_USER;
        this.statusUpdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }


    public boolean logout() {
        refreshToken = null;
        return refreshToken==null? true: false;
    }

}
