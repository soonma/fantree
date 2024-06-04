package com.team13.fantree.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public boolean login(LoginRequestDto requestDto) {
		User findUser = userRepository.findByUsername(requestDto.getUsername()).get();
		if (findUser == null || !findUser.getPassword().equals(requestDto.getPassword()))
			return false;
		return true;
	}

	@Transactional
	public boolean logout(Long id) {
		User user = userRepository.findById(id).get();
		return user.logout();
	}
}