package com.team13.fantree.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
	POST_NOT_FOUND(HttpStatus.NOT_FOUND,
		"글이 없습니다.");


	private final HttpStatus httpStatus;
	private final String message;
}


