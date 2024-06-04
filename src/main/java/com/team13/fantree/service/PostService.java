package com.team13.fantree.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.team13.fantree.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
}
