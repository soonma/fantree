package com.team13.fantree.service;

import com.team13.fantree.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
}