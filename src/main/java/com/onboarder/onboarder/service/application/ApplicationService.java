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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    // 유저가 공고에 지원했는지 확인하고 기록
    public void applyToJobPost(ApplicationRequestDto requestDto, String userEmail) {
        // 1. 유효성 검증 : 사용자, 공고 존재 여부 확인
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        JobPost jobPost = jobPostRepository.findById(requestDto.getJobPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 채용 공고가 존재하지 않습니다."));


        // 2. 지원 상태 설정
        Application application = Application.builder()
                .user(user)
                .jobPost(jobPost)
                .build();

        applicationRepository.save(application);
    }


    // 유저가 지원한 채용 공고를 조회
    public List<Application> getApplicationsByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 유저가 지원한 채용 공고를 조회
        return applicationRepository.findAllByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."));
    }

    // 유저가 지원한 채용 공고를 상태별로 조회
    public List<Application> getApplicationsByUserAndStatus(int userId, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));


        return applicationRepository.findAllByUserAndStatus(user, status)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."));
    }

    // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
    public List<Application> getApplicationsByUserAndJobPostId(int userId, int jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 채용 공고가 존재하지 않습니다."));

        // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
        return applicationRepository.findAllByUserAndJobPost(user, jobPost)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."));
    }
}
