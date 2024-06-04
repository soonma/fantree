package com.team13.fantree.service;

import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.exception.DataNotFoundException;
import com.team13.fantree.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ProfileResponseDto update(Long userId, ProfileRequestDto requestDto) {
        User user = findProfileById(userId);
        user.update(requestDto);
        return ProfileResponseDto.toDto(user);
    }

    @Transactional
    public void passwordUpdate(Long userId, PasswordRequestDto requestDto) {
        User user = findProfileById(userId);

        // 비밀번호 수정 시, 본인 확인을 위해 입력한 현재 비밀번호가 일치하지 않은 경우
        if (!requestDto.getBeforePassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 형식이 올바르지 않은 경우
        if (!isValidPasswordFormat(requestDto.getNewPassword())) {
            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
        }

        // 현재 비밀번호와 동일한 비밀번호로 수정하는 경우
        if (requestDto.getBeforePassword().equals(requestDto.getNewPassword())) {
            throw new IllegalArgumentException("현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
        }

        user.passwordUpdate(requestDto.getNewPassword());
    }

    public ProfileResponseDto findById(Long userId) {
        return ProfileResponseDto.toDto(findProfileById(userId));
    }

    protected User findProfileById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("해당 Id에 맞는 프로필을 찾을 수 없습니다."));
    }

    private boolean isValidPasswordFormat(String password) {
        // 최소 10글자 이상, 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함하는 정규식
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).{10,}$";
        return password.matches(passwordPattern);
    }

}
