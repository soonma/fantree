package com.team13.fantree.service;


import com.team13.fantree.dto.PostRequestDto;
import com.team13.fantree.dto.PostResponseDto;
import com.team13.fantree.entity.Post;
import com.team13.fantree.entity.User;
import com.team13.fantree.repository.PostRepository;
import com.team13.fantree.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.team13.fantree.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    //임시 테스트용 UserRepository
    private final UserRepository userRepository;


    public PostResponseDto createPost(PostRequestDto requestDto) {
        //임시 테스트  나중에 주석 처리 할 예정
        User user = userRepository.findById(1L).get();

        Post post = new Post(requestDto,user);

        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> findAllPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreateAtDesc();

        if(posts.isEmpty()){
            return null;
        } else {
            return findContent(posts);
        }

    }

    public PostResponseDto findPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 정보를 찾을수 없습니다.")
        );
            return new PostResponseDto(post);

    }

    @Transactional
    public String updatePost(long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 정보를 찾을수 없습니다.")
        );
        post.setContent(requestDto.getContent());
        postRepository.save(post);

        return "저장 성공했습니다.";
    }

    @Transactional
    public String deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 정보를 찾을수 없습니다.")
        );
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
