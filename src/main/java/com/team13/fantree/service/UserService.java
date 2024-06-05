package com.team13.fantree.service;

import com.team13.fantree.dto.SignUpRequestDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import com.team13.fantree.dto.LoginRequestDto;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
        User user = userRepository.findById(id).get();
        if(!password.equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        if(user.getStatus().equals(UserStatusEnum.NON_USER)){
            throw new IllegalArgumentException("이미 탈퇴한 회원입니다.");
        }
        user.withDraw();
    }

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