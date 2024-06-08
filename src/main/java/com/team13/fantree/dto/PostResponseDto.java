package com.team13.fantree.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.team13.fantree.entity.Post;
import com.team13.fantree.entity.User;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String username;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public static ProfileResponseDto toDto(User user) {
        return new ProfileResponseDto(user);
    }
}
