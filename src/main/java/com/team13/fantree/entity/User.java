package com.team13.fantree.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.team13.fantree.dto.ProfileRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

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

	public void update(ProfileRequestDto requestDto, String newEncodePw) {
		if (requestDto.getName() != null) {
			this.name = requestDto.getName();
		}
		if (requestDto.getEmail() != null) {
			this.email = requestDto.getEmail();
		}
		if (requestDto.getHeadline() != null) {
			this.headline = requestDto.getHeadline();
		}
		if (newEncodePw != null) {
			this.password = newEncodePw;
		}

	}

	public void passwordUpdate(String password) {
		this.password = password;
	}

	public void withDraw() {
		this.status = UserStatusEnum.NON_USER;
		this.statusUpdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		this.refreshToken = null;
	}

	public void logout() {
		refreshToken = null;
	}

}
