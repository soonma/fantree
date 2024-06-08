package com.team13.fantree.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team13.fantree.dto.PasswordRequestDto;
import com.team13.fantree.dto.ProfileRequestDto;
import com.team13.fantree.dto.ProfileResponseDto;
import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.exception.MismatchException;
import com.team13.fantree.exception.NotFoundException;
import com.team13.fantree.exception.UserErrorCode;
import com.team13.fantree.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void logout(Long id) {
		User user = findById(id);
		user.logout();
	}

	public void signup(SignUpRequestDto requestDto) {
		String username = requestDto.getUsername();
		String password = passwordEncoder.encode(requestDto.getPassword());

		if (userRepository.findByUsername(username).isPresent()) {
			if (userRepository.findByUsername(username).get().getStatus().equals(UserStatusEnum.NON_USER)) {
				throw new MismatchException(UserErrorCode.WITHDRAW_USER);
			}
			throw new MismatchException(UserErrorCode.DUPLICATED_USER);
		}
		User user = new User(username, password, requestDto.getName(), requestDto.getEmail(), requestDto.getHeadline());
		userRepository.save(user);
	}

	@Transactional
	public void withDraw(Long id, String password) {
		User user = findById(id);
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new MismatchException(UserErrorCode.PW_MISMATCH);
		}
		if (user.getStatus().equals(UserStatusEnum.NON_USER)) {
			throw new NotFoundException(UserErrorCode.WITHDRAW_USER);
		}
		user.withDraw();
	}

	@Transactional
	public ProfileResponseDto update(Long userId, ProfileRequestDto requestDto) {
		User user = findById(userId);
		String newEncodePw = null;

		if(requestDto.getPassword() != null) {
			if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
				newEncodePw = passwordEncoder.encode(requestDto.getNewPassword());
			}
		}
		user.update(requestDto.getName(), requestDto.getEmail(), requestDto.getHeadline(), newEncodePw);
		return new ProfileResponseDto(user);
	}

	public ProfileResponseDto getProfile(Long userId) {
		return new ProfileResponseDto(findById(userId));
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);
	}

	public void refreshTokenCheck(String username, String refreshToken) {
		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new NotFoundException(UserErrorCode.USER_NOT_FOUND)
		);
		if (!user.getRefreshToken().equals(refreshToken)) {
			throw new MismatchException(UserErrorCode.REFRESH_TOKEN_MISMATCH);
		}

	}
}
