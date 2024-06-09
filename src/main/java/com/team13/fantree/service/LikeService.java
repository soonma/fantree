package com.team13.fantree.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.team13.fantree.dto.LikeResponseDto;
import com.team13.fantree.entity.ContentEnumType;
import com.team13.fantree.entity.Like;
import com.team13.fantree.exception.CommonErrorCode;
import com.team13.fantree.exception.NotFoundException;
import com.team13.fantree.exception.PostErrorCode;
import com.team13.fantree.repository.CommentRepository;
import com.team13.fantree.repository.LikeRepository;
import com.team13.fantree.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikeService {

	private final LikeRepository likeRepository;
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	public LikeResponseDto createLike(long userId, long typeId, String type) {
		if (Objects.equals(type, ContentEnumType.COMMENT.getType())) {
			commentRepository.findById(typeId).orElseThrow(
				// comment 예외 발생
			);
		} else if (Objects.equals(type, ContentEnumType.POST.getType())) {
			postRepository.findById(typeId).orElseThrow(
				() -> new NotFoundException(PostErrorCode.POST_NOT_FOUND)
			);
		} else {
			throw new NotFoundException(CommonErrorCode.RESOURCE_NOT_FOUND);
		}

		Like like = likeRepository.save(new Like(userId, typeId, type));
		return new LikeResponseDto(like);
	}
}
