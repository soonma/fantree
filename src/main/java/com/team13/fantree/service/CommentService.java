package com.team13.fantree.service;

import com.team13.fantree.dto.CommentRequestDto;
import com.team13.fantree.dto.CommentResponseDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;


    public void createComment(CommentRequestDto requestDto, User user) {

    }

//    public CommentResponseDto findCommentById(long id) {
//
//    }
}
