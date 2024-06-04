package com.team13.fantree.controller;

import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	  public ResponseEntity login(@Valid @RequestBody LoginRequestDto requestDto) {
		  boolean login = userService.login(requestDto);
		  return new ResponseEntity(login ? "로그인 성공" : "로그인 실패",
			login ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

    @PutMapping("/withDraw/{id}")
    public ResponseEntity<String> withDraw(@PathVariable Long id, String password) {
        userService.withDraw(id, password);
        return ResponseEntity.status(201).body("회원탈퇴에 성공했습니다.");
    }

    @PutMapping("/logout/{id}")
	  public ResponseEntity logout(@PathVariable Long id) {
		  boolean logout = userService.logout(id);
		  return new ResponseEntity(logout ? "로그아웃 성공" : "로그아웃 실패",
			logout ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

    @PutMapping("/profile/{id}")
    public ResponseEntity profileUpdate(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto){
        return null;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity getProfile(@PathVariable Long id) {
        return null;
    }

}
