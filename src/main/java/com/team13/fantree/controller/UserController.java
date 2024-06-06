package com.team13.fantree.controller;

import com.team13.fantree.security.UserDetailsImpl;
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

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto) {
        userService.login(requestDto);
        return ResponseEntity.ok().body("로그인 성공");
    }

    @PostMapping("/out")
    public ResponseEntity logout( @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.logout(userDetails.getUser().getId());
        return ResponseEntity.ok().body("로그아웃 성공");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.status(201).body("회원가입에 성공했습니다.");
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> withDraw( @AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam("password") String password) {
        userService.withDraw(userDetails.getUser().getId(), password);
        return ResponseEntity.status(201).body("회원탈퇴에 성공했습니다.");
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<ProfileResponseDto> profileUpdate(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto){
        return ResponseEntity.ok().body(userService.update(id, requestDto));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> passwordUpdate(@PathVariable Long id, @Valid @RequestBody PasswordRequestDto requestDto) {
        userService.passwordUpdate(id, requestDto);
        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getProfile(id));
    }

}
