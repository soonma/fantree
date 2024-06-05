package com.team13.fantree.service;

import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.exception.NotFoundException;
import com.team13.fantree.exception.UserErrorCode;
import com.team13.fantree.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.team13.fantree.dto.LoginRequestDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public ProfileResponseDto update(Long id, ProfileRequestDto requestDto) {
        User user = findById(id);
        user.update(requestDto);
        return new ProfileResponseDto(user);
    }

    @Transactional
    public void passwordUpdate(Long id, PasswordRequestDto requestDto) {
        User user = findById(id);
        if (!requestDto.getBeforePassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
        if (requestDto.getBeforePassword().equals(requestDto.getNewPassword())) {
            throw new IllegalArgumentException("현재 비밀번호와 동일한 비밀번호로 변경할 수 없습니다.");
        }
        user.passwordUpdate(requestDto.getNewPassword());
    }

    public ProfileResponseDto getProfile(Long id) {
        return new ProfileResponseDto(findById(id));
    }

    public void signup(SignUpRequestDto requestDto){
        String username = requestDto.getUsername();
        if (userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException("중복된 회원입니다.");
        }
        User user = new User(requestDto);
        userRepository.save(user);
    }

    @Transactional
    public void withDraw(Long id, String password){
        User user = findById(id);
        if(!password.equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        if(user.getStatus().equals(UserStatusEnum.NON_USER)){
            throw new IllegalArgumentException("이미 탈퇴한 회원입니다.");
        }
        user.withDraw();
    }

	public boolean login(LoginRequestDto requestDto) {
		User findUser = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);
		if (!findUser.getPassword().equals(requestDto.getPassword()))
			throw new NotFoundException(UserErrorCode.USER_NOT_FOUND);
		return true;
	}

	@Transactional
	public boolean logout(Long id) {
		User user = findById(id);
		return user.logout();
	}

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()->new NotFoundException(UserErrorCode.USER_NOT_FOUND)
        );
    }

}