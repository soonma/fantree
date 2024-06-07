package com.team13.fantree.service;

import com.team13.fantree.dto.PostRequestDto;
import com.team13.fantree.dto.PostResponseDto;
import com.team13.fantree.entity.Post;
import com.team13.fantree.entity.User;
import com.team13.fantree.exception.MismatchException;
import com.team13.fantree.exception.NotFoundException;
import com.team13.fantree.exception.UserErrorCode;
import com.team13.fantree.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	@Transactional
	public PostResponseDto createPost(PostRequestDto requestDto, User user) {
		Post post = new Post(requestDto, user);
		log.info("Creating post: {}", post.getUser().getUsername());
		postRepository.save(post);
		return new PostResponseDto(post);
	}

	public List<PostResponseDto> findAllPosts() {
		List<Post> posts = postRepository.findAllByOrderByCreateAtDesc();
		return findContent(posts);
	}

	public PostResponseDto findPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(
			() -> new NotFoundException(UserErrorCode.POST_NOT_FOUND)
		);
		return new PostResponseDto(post);

	}

	@Transactional
	public String updatePost(long id, PostRequestDto requestDto, User user) {
		Post post = postRepository.findById(id).orElseThrow(
			() -> new NotFoundException(UserErrorCode.POST_NOT_FOUND)
		);
		if (post.getUser().getId() != user.getId()) {
			throw new MismatchException(UserErrorCode.USER_MISMATCH_FOR_POST);
		}
		post.setContent(requestDto.getContent());
		postRepository.save(post);
		return "저장 성공했습니다.";
	}

	@Transactional
	public String deletePost(long id, User user) {
		Post post = postRepository.findById(id).orElseThrow(
			() -> new NotFoundException(UserErrorCode.POST_NOT_FOUND)
		);
		if (post.getUser().getId() != user.getId()) {
			throw new MismatchException(UserErrorCode.USER_MISMATCH_FOR_POST);
		}
		postRepository.delete(post);
		return "성공했습니다";
	}

	public List<PostResponseDto> findContent(List<Post> posts) {
		List<PostResponseDto> postResponseDtos = new ArrayList<>();
		for (Post post : posts) {
			PostResponseDto postResponseDto = new PostResponseDto(post);
			postResponseDtos.add(postResponseDto);
		}
		return postResponseDtos;
	}

}
