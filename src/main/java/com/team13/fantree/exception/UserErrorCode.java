package com.team13.fantree.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
	USER_NOT_FOUND(HttpStatus.NOT_FOUND,
			"유저가 없습니다."),
	PW_MISMATCH(HttpStatus.NOT_FOUND,
			"유저이름과 비밀번호 불일치"),
	PASSWORD_MISMATCH(HttpStatus.NOT_FOUND,
			"현재 비밀번호와 뷸일치"),
	PASSWORD_MATCH(HttpStatus.NOT_FOUND,
			"현재 비밀번호와 일치");

	private final HttpStatus httpStatus;
	private final String message;
}
