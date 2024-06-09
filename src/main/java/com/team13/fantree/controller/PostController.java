package com.team13.fantree.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
	private static final String noPostsMessage = "먼저 작성하여 소식을 알려보세요!";
	private static final String deletePostSuccessMessage = "게시글이 삭제되었습니다";

	@PostMapping
	public ResponseEntity<PostResponseDto> createPost(
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		PostResponseDto post = postService.createPost(requestDto, userDetails.getUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	@GetMapping
	public ResponseEntity<Object> findAllPosts(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size) {

		List<PostResponseDto> postsDtoList = postService.findAllPosts(page, size);

		return ResponseEntity.ok().body(postsDtoList.isEmpty()
			? noPostsMessage : postsDtoList);
	}

	@GetMapping("/period")
	public ResponseEntity<List<PostResponseDto>> findAllPostsByPeriod(
		@RequestParam String startDate,
		@RequestParam String endDate) {

		List<PostResponseDto> postsDtoList = postService.findAllPostsPeriod(startDate, endDate);

		return ResponseEntity.ok().body(postsDtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> findPostById(@PathVariable Long id) {

		PostResponseDto responseDto = postService.findPostById(id);
		return ResponseEntity.ok().body(responseDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponseDto> updatePost(@PathVariable long id,
		@RequestBody PostRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		PostResponseDto post = postService.updatePost(id, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(post);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		postService.deletePost(id, userDetails.getUser());
		return ResponseEntity.ok().body(deletePostSuccessMessage);
	}
}
