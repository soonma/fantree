package com.team13.fantree.controller;

import com.team13.fantree.dto.PostRequestDto;
import com.team13.fantree.dto.PostResponseDto;
import com.team13.fantree.entity.Post;
import com.team13.fantree.security.UserDetailsImpl;
import com.team13.fantree.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto,userDetails.getUser());
        return ResponseEntity.ok().body("Post created");
    }

    @GetMapping
    public ResponseEntity findAllPosts() {
        List<PostResponseDto> responseDtos = postService.findAllPosts();

        if (responseDtos.isEmpty()) {
            return ResponseEntity.status(200).body("먼저 작성하여 소식을 알려보세요!");
        } else {
            return ResponseEntity.status(200).body(postService.findAllPosts());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findPostById(@PathVariable Long id) {
        PostResponseDto ResponseDto = postService.findPostById(id);
        if (ResponseDto == null) {
            return ResponseEntity.status(404).body("Post not found");
        } else {
            return ResponseEntity.status(200).body(ResponseDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(id,requestDto,userDetails.getUser());
        return ResponseEntity.status(200).body("Post updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id,userDetails.getUser());
        return ResponseEntity.status(200).body("Post deleted");
    }
}
