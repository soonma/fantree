package com.team13.fantree.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team13.fantree.dto.PostRequestDto;
import com.team13.fantree.dto.PostResponseDto;
import com.team13.fantree.security.UserDetailsImpl;
import com.team13.fantree.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@PostMapping
	public ResponseEntity createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		postService.createPost(requestDto, userDetails.getUser());
		return ResponseEntity.ok().body("Post created");
	}

	@GetMapping
	public ResponseEntity findAllPosts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size) {

		List<PostResponseDto> postsDtoList = postService.findAllPosts(page, size);

		return ResponseEntity.status(200).body(postsDtoList.isEmpty()
			? "먼저 작성하여 소식을 알려보세요!" : postsDtoList);
	}

	@GetMapping("/period")
	public ResponseEntity findAllPostsByPeriod(
		@RequestParam String startDate,
		@RequestParam String endDate) {

		List<PostResponseDto> postsDtoList = postService.findAllPostsPeriod(startDate, endDate);

		return ResponseEntity.status(200).body(postsDtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity findPostById(@PathVariable Long id) {
		PostResponseDto ResponseDto = postService.findPostById(id);
		return ResponseEntity.status(200).body(ResponseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		postService.updatePost(id, requestDto, userDetails.getUser());
		return ResponseEntity.status(200).body("Post updated");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deletePost(@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		postService.deletePost(id, userDetails.getUser());
		return ResponseEntity.status(200).body("Post deleted");
	}
}
