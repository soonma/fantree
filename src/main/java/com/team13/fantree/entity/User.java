package com.team13.fantree.entity;



import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.SignUpRequestDto;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
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

    public User(String username, String password, String name, String email, String headline) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.headline = headline;
        this.status = UserStatusEnum.USER;
    }

    public void update(ProfileRequestDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.headline = requestDto.getHeadline();
        this.password = requestDto.getPassword();
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }

    public void withDraw(){
        this.status = UserStatusEnum.NON_USER;
        this.statusUpdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.refreshToken = null;
    }

    public void logout() {
        refreshToken = null;
    }

}
