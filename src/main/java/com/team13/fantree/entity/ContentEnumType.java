package com.team13.fantree.entity;

import lombok.Getter;

@Getter
public enum ContentEnumType {
	POST("post"), COMMENT("comment");

	private final String type;

	ContentEnumType(String type) {
		this.type = type;
	}
}
