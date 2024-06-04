package com.team13.fantree.controller;

import com.team13.fantree.dto.*;
import com.team13.fantree.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDto requestDto) {
        return null;
    }

    @PutMapping("/withDraw/{id}")
    public ResponseEntity withDraw(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/logout/{id}")
    public ResponseEntity logout(@PathVariable Long id) {
        return null;
    }

    @PatchMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponseDto> profileUpdate(@PathVariable Long userId, @RequestBody ProfileRequestDto requestDto){
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
