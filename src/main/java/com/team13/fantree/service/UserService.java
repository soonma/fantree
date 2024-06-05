package com.team13.fantree.service;

import org.springframework.stereotype.Service;

import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.exception.DataNotFoundException;
import com.team13.fantree.exception.NotFoundException;
import com.team13.fantree.exception.UserErrorCode;
import com.team13.fantree.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

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

	public void signup(SignUpRequestDto requestDto) {
		String username = requestDto.getUsername();

		if (!isValidUsername(requestDto.getUsername())) {
			throw new IllegalArgumentException("ID 형식이 올바르지 않습니다.");
		}

		if (userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("중복된 회원입니다.");
		}

		//        if(!isValidPasswordFormat(requestDto.getPassword())){
		//            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
		//        }

		User user = new User(requestDto);
		userRepository.save(user);
	}

	@Transactional
	public void withDraw(Long id, String password) {
		User user = userRepository.findById(id).get();
		if (!password.equals(user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		if (user.getStatus().equals(UserStatusEnum.NON_USER)) {
			throw new IllegalArgumentException("이미 탈퇴한 회원입니다.");
		}
		user.withDraw();
	}

	private boolean isValidPasswordFormat(String password) {
		String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).{10,}$";
		return password.matches(passwordPattern);
	}

	private boolean isValidUsername(String username) {
		String usernamePattern = "^[a-zA-Z0-9_-]{10,20}$";
		return username.matches(usernamePattern);
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
		User user = userRepository.findById(id).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);
		return user.logout();
	}

}