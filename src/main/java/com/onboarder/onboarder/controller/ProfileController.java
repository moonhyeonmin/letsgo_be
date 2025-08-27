package com.onboarder.onboarder.controller;

import com.onboarder.onboarder.dto.profile.ProfileStatsDto;
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
@RequestMapping("/api/users/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<UserProfileDto> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileDto profile = profileService.getUserProfile(userDetails.getUsername());
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> updateUserProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ProfileUpdateRequestDto requestDto) {
        UserProfileDto updatedProfile = profileService.updateUserProfile(userDetails.getUsername(), requestDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("/stats")
    public ResponseEntity<ProfileStatsDto> getProfileStats(@AuthenticationPrincipal UserDetails userDetails) {
        ProfileStatsDto stats = profileService.getProfileStats(userDetails.getUsername());
        return ResponseEntity.ok(stats); // stats 대신 빈 응답 반환
    }
}
