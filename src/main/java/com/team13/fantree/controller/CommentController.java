package com.team13.fantree.controller;

import com.team13.fantree.dto.CommentRequestDto;
import com.team13.fantree.dto.CommentResponseDto;
import com.team13.fantree.security.UserDetailsImpl;
import com.team13.fantree.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(requestDto, userDetails.getUser());
        return ResponseEntity.ok().body("Comment created");
    }

//    @GetMapping
//    public ResponseEntity findAllComments(@PathVariable long id) {
//        CommentResponseDto ResponseDto = commentService.findCommentById(id);
//    }


}
