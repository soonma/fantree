package com.team13.fantree.service;

import com.team13.fantree.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    PostRepository postRepository;
}
