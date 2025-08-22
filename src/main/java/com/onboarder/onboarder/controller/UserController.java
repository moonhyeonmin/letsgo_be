package com.onboarder.onboarder.controller;

import com.onboarder.onboarder.dto.member.MemberCreateRequestDto;
import com.onboarder.onboarder.dto.member.MemberResponseDto;
import com.onboarder.onboarder.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입 API 엔드포인트
     *
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody MemberCreateRequestDto requestDto) {
        // 회원 가입 요청 DTO를 사용하여 회원 가입 처리
        userService.signUp(requestDto);

        // 회원 가입 성공 시 201 Created 응답 및 회원정보 반환
        return ResponseEntity.status(201).build();
    }
}
