package com.team13.fantree.entity;


import com.team13.fantree.dto.ProfileRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String name;

    private String email;

    private String headline;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    private String refreshToken;

    private String statusUpdate;

    public void update(ProfileRequestDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.headline = requestDto.getHeadline();
        this.password = requestDto.getPassword();
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }

}
