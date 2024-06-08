package com.team13.fantree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.jwt.JwtTokenHelper;
import com.team13.fantree.security.UserDetailsImpl;
import com.team13.fantree.service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final JwtTokenHelper jwtTokenHelper;

	@PostMapping("/logout")
	public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		userService.logout(userDetails.getUser().getId());
		return ResponseEntity.ok().body("로그아웃 성공");
	}

	@PostMapping()
	public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
		userService.signup(requestDto);
		return ResponseEntity.status(201).body("회원가입에 성공했습니다.");
	}

	@DeleteMapping
	public ResponseEntity<String> withDraw(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("password") String password) {
		userService.withDraw(userDetails.getUser().getId(), password);
		return ResponseEntity.status(201).body("회원탈퇴에 성공했습니다.");
	}

	@PutMapping
	public ResponseEntity<ProfileResponseDto> profileUpdate(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProfileRequestDto requestDto) {
		Long userId = userDetails.getUser().getId();
		return ResponseEntity.ok().body(userService.update(userId, requestDto));
	}

	@GetMapping
	public ResponseEntity<ProfileResponseDto> getProfile(
		@RequestHeader(JwtTokenHelper.AUTHORIZATION_HEADER) String accessToken,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		log.info(accessToken);
		return ResponseEntity.ok().body(userService.getProfile(userDetails.getUser().getId()));
	}

	@GetMapping("/refresh")
	public ResponseEntity refresh(
		@RequestHeader(JwtTokenHelper.AUTHORIZATION_HEADER) String accessToken,
		@RequestHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER) String refreshToken
	) {
		Claims claims = jwtTokenHelper.getExpiredAccessToken(accessToken);
		String username = claims.getSubject();
		String status = claims.get("status").toString();
		UserStatusEnum statusEnum = UserStatusEnum.valueOf(status);

		userService.refreshTokenCheck(username, refreshToken);

		String newAccessToken = jwtTokenHelper.createToken(username, statusEnum);
		return ResponseEntity.ok()
			.header(JwtTokenHelper.AUTHORIZATION_HEADER, newAccessToken)
			.body("토큰을 재발행 했습니다.  " + newAccessToken);
	}

}
