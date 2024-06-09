package com.team13.fantree.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Like extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;

	private long contentId;

	@Enumerated(EnumType.STRING)
	private ContentEnumType contentType;

	@Builder
	public Like(long userId, long typeId, String type) {
		this.userId = userId;
		this.contentType = ContentEnumType.valueOf(type);
		this.contentId = typeId;
	}
}
