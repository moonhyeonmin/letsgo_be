package com.onboarder.onboarder.service.profile;

import com.onboarder.onboarder.domain.user.User;
import com.onboarder.onboarder.dto.profile.OnboardingStatusDto;
import com.onboarder.onboarder.dto.profile.ProfileStatsDto;
import com.onboarder.onboarder.dto.profile.ProfileUpdateRequestDto;
import com.onboarder.onboarder.dto.profile.UserProfileDto;
import com.onboarder.onboarder.repository.ApplicationRepository;
import com.onboarder.onboarder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    // UserFavoriteRepository, etc.를 추가해야 합니다.

    @Transactional(readOnly = true)
    public UserProfileDto getUserProfile(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.getSkills().size();
        return new UserProfileDto(user);
    }

    @Transactional
    public UserProfileDto updateUserProfile(String userEmail, ProfileUpdateRequestDto requestDto) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.updateProfile(
                requestDto.getNickname(),
                requestDto.getName(),
                requestDto.getPhone(),
                requestDto.getLocation(),
                requestDto.getExperience(),
                requestDto.getPosition(),
                requestDto.getBio(),
                requestDto.getSkills(),
                requestDto.getEducation(),
                requestDto.getCompany()
        );



        userRepository.save(user);
        return new UserProfileDto(user);
    }

    @Transactional(readOnly = true)
    public OnboardingStatusDto checkOnboardingStatus(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        boolean isCompleted = user.getName() != null && !user.getName().isEmpty();

        return new OnboardingStatusDto(isCompleted);
    }


    @Transactional(readOnly = true)
    public ProfileStatsDto getProfileStats(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 통계 데이터 계산 (임시 로직)
        long totalApplications = applicationRepository.countByUser(user);
        long documentsPassedCount = 0; // 로직 구현 필요
        long interviewsInProgressCount = 0; // 로직 구현 필요
        long bookmarksCount = 0; // 로직 구현 필요

        return new ProfileStatsDto(totalApplications, documentsPassedCount, interviewsInProgressCount, bookmarksCount);
    }
}
