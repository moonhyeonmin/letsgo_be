package com.onboarder.onboarder.controller;


import com.onboarder.onboarder.dto.member.LoginRequestDto;
import com.onboarder.onboarder.dto.member.LoginResponseDto;
import com.onboarder.onboarder.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login") // RefreshToken 사용하는 구조 추후에 구성
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        String token = authService.login(requestDto);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}