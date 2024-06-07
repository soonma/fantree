package com.team13.fantree.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team13.fantree.dto.LoginRequestDto;
import com.team13.fantree.entity.User;
import com.team13.fantree.entity.UserStatusEnum;
import com.team13.fantree.exception.MismatchException;
import com.team13.fantree.exception.UserErrorCode;
import com.team13.fantree.jwt.JwtTokenHelper;
import com.team13.fantree.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtTokenHelper jwtTokenHelper;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenHelper jwtTokenHelper, UserRepository userRepository) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userRepository = userRepository;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            User user = userRepository.findByUsername(requestDto.getUsername()).get();

            if(user.getStatus().equals(UserStatusEnum.NON_USER)) {
                throw new MismatchException(UserErrorCode.WITHDRAW_USER);
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("상태 : " +response.getStatus()+", 로그인 성공");

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserStatusEnum status = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getStatus();

        log.info("username: {}", username);
        log.info("status: {}", status);

        String accessToken = jwtTokenHelper.createToken(username, status);
        String refreshToken =  jwtTokenHelper.createRefreshToken();
        response.addHeader(JwtTokenHelper.AUTHORIZATION_HEADER, accessToken);
        response.addHeader(JwtTokenHelper.REFRESH_TOKEN_HEADER, refreshToken);
        jwtTokenHelper.saveRefreshToken(username,refreshToken);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }

}