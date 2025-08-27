package com.onboarder.onboarder.controller;

import com.onboarder.onboarder.dto.profile.OnboardingStatusDto;
import com.onboarder.onboarder.dto.profile.ProfileUpdateRequestDto;
import com.onboarder.onboarder.dto.profile.UserProfileDto;
import com.onboarder.onboarder.service.profile.ProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/onboarding")
@RequiredArgsConstructor
public class OnboardingController {

    private final ProfileService profileService;


    @PostMapping
    public ResponseEntity<UserProfileDto> startOnboarding(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ProfileUpdateRequestDto requestDto
            ) {
        // 온보딩 프로세스 시작 로직 구현
        // 예: 기본 프로필 생성, 환영 이메일 발송 등
        UserProfileDto profile = profileService.updateUserProfile(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/status")
    public ResponseEntity<OnboardingStatusDto> checkOnboardingStatus(@AuthenticationPrincipal UserDetails userDetails) {
        OnboardingStatusDto status = profileService.checkOnboardingStatus(userDetails.getUsername());
        return ResponseEntity.ok(status);
    }

    
}
