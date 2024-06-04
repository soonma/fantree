package com.team13.fantree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.team13.fantree.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
}