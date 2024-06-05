package com.team13.fantree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
		userService.signup(requestDto);
		return ResponseEntity.status(201).body("회원가입에 성공했습니다.");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto) {
		userService.login(requestDto);
		return ResponseEntity.ok().body("로그인 성공");
	}

	@PutMapping("/withDraw/{id}")
	public ResponseEntity<String> withDraw(@PathVariable Long id, String password) {
		userService.withDraw(id, password);
		return ResponseEntity.status(201).body("회원탈퇴에 성공했습니다.");
	}

	@PutMapping("/logout/{id}")
	public ResponseEntity logout(@PathVariable Long id) {
		userService.logout(id);
		return ResponseEntity.ok().body("로그아웃 성공");

	}

	@PatchMapping("/profile/{userId}")
	public ResponseEntity<ProfileResponseDto> profileUpdate(@PathVariable Long userId, @RequestBody ProfileRequestDto requestDto) {
		return ResponseEntity.ok().body(userService.update(userId, requestDto));
	}

	@PutMapping("/profile/{userId}")
	public ResponseEntity<String> passwordUpdate(@PathVariable Long userId, @RequestBody PasswordRequestDto requestDto) {
		userService.passwordUpdate(userId, requestDto);
		return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
	}

	@GetMapping("/profile/{userId}")
	public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long userId) {
		return ResponseEntity.ok().body(userService.findById(userId));
	}

}
