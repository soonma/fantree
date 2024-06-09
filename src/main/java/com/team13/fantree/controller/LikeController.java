package com.team13.fantree.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team13.fantree.dto.LikeResponseDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.security.UserDetailsImpl;
import com.team13.fantree.service.LikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/Like")
@RestController
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/{typeId}")
	public ResponseEntity<LikeResponseDto> like(
		@PathVariable long typeId,
		@RequestParam("type") String type,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		LikeResponseDto responseDto = likeService.createLike(
			userDetails.getUser().getId(), typeId, type);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}
}
