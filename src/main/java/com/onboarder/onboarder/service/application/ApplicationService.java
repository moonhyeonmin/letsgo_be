package com.onboarder.onboarder.service.application;


import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.domain.jobpost.JobPost;
import com.onboarder.onboarder.domain.user.User;
import com.onboarder.onboarder.dto.application.ApplicationRequestDto;
import com.onboarder.onboarder.repository.ApplicationRepository;
import com.onboarder.onboarder.repository.JobPostRepository;
import com.onboarder.onboarder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    // 유저가 공고에 지원했는지 확인하고 기록
    public void applyToJobPost(ApplicationRequestDto requestDto, Integer userId) {
        // 1. 유효성 검증 : 사용자, 공고 존재 여부 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        JobPost jobPost = jobPostRepository.findById(requestDto.getJobPostId());
        if (jobPost == null) {
            throw new IllegalArgumentException("해당 채용 공고를 찾을 수 없습니다.");
        }

        // 2. 지원 상태 설정
        Application application = Application.builder()
                .user(user)
                .jobPost(jobPost)
                .build();

        applicationRepository.save(application);


    }
}
