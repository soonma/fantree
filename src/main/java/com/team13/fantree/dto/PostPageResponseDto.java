package com.team13.fantree.dto;

import org.springframework.data.domain.Page;

import com.team13.fantree.entity.Post;

import lombok.Getter;

@Getter
public class PostPageResponseDto {
	public PostPageResponseDto(Page<Post> allPosts) {
		for (Post allPost : allPosts) {

		}
	}
}
