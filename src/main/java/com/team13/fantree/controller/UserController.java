package com.team13.fantree.controller;

import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.jwt.JwtTokenHelper;
import com.team13.fantree.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

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

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(201).body("회원가입에 성공했습니다.");
    }

    @DeleteMapping()
    public ResponseEntity<String> withDraw(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("password") String password) {
        userService.withDraw(userDetails.getUser().getId(), password);
        return ResponseEntity.status(201).body("회원탈퇴에 성공했습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> profileUpdate(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto) {
        return ResponseEntity.ok().body(userService.update(id, requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> passwordUpdate(@PathVariable Long id, @Valid @RequestBody PasswordRequestDto requestDto) {
        userService.passwordUpdate(id, requestDto);
        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getProfile(id));
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
