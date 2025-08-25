package com.onboarder.onboarder.service.application;


import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.domain.application.ApplicationQuestion;
import com.onboarder.onboarder.domain.jobpost.JobPost;
import com.onboarder.onboarder.domain.user.User;
import com.onboarder.onboarder.dto.application.ApplicationRequestDto;
import com.onboarder.onboarder.dto.application.ApplicationResponseDto;
import com.onboarder.onboarder.repository.ApplicationRepository;
import com.onboarder.onboarder.repository.JobPostRepository;
import com.onboarder.onboarder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    // 유저가 공고에 지원했는지 확인하고 기록
    @Transactional
    public ApplicationResponseDto applyToJobPost(ApplicationRequestDto requestDto, String userEmail) {
        // 1. 유효성 검증 : 사용자, 공고 존재 여부 확인
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        JobPost jobPost = jobPostRepository.findById(requestDto.getJobPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 채용 공고가 존재하지 않습니다."));

        // 이미 지원한 공고인지 확인
        if (applicationRepository.findAllByUserAndJobPost(user, jobPost)
                .map(list -> !list.isEmpty())
                .orElse(false)) {
            throw new IllegalArgumentException("이미 지원한 채용 공고입니다.");
        }

        // 2. 지원 상태 설정
        Application application = Application.builder()
                .user(user)
                .jobPost(jobPost)
                .status(requestDto.getStatus())
                .memo(requestDto.getMemo())
                .build();

        // 3. 질문 답변 엔티티 생성 및 리스트에 추가
        List<ApplicationQuestion> questions = requestDto.getQuestions().stream()
                        .map(applicationQuestionDto -> ApplicationQuestion.builder()
                                .question(applicationQuestionDto.getQuestion())
                        .answer(applicationQuestionDto.getAnswer())
                                .application(application)
                                .build())
                                .toList();

        application.addQuestions(questions);

        applicationRepository.save(application);

        return new ApplicationResponseDto(application);
    }


    // 유저가 지원한 채용 공고를 조회
    @Transactional(readOnly = true)
    public List<ApplicationResponseDto> getApplicationsByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 유저가 지원한 채용 공고를 조회
        return applicationRepository.findAllByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."))
                .stream()
                .map(ApplicationResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저가 지원한 채용 공고를 상태별로 조회
    public List<Application> getApplicationsByUserAndStatus(int userId, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));


        return applicationRepository.findAllByUserAndStatus(user, status)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."));
    }

    // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
    public List<ApplicationResponseDto> getApplicationsByUserAndJobPostId(int userId, int jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 채용 공고가 존재하지 않습니다."));

        // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
        return applicationRepository.findAllByUserAndJobPost(user, jobPost)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."))
                .stream()
                .map(ApplicationResponseDto::new)
                .collect(Collectors.toList());
    }

    // 이메일로 유저가 지원한 채용 공고를 조회
    @Transactional(readOnly = true)
    public List<ApplicationResponseDto> getApplicationByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 유저가 지원한 채용 공고를 조회
        return applicationRepository.findAllByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."))
                .stream()
                .map(ApplicationResponseDto::new)
                .collect(Collectors.toList());
    }

    // 이메일로 유저가 지원한 채용 공고를 상태별로 조회
    public List<ApplicationResponseDto> getApplicationsByEmailAndStatus(String userEmail, String status) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return applicationRepository.findAllByUserAndStatus(user, status)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 지원한 채용 공고가 없습니다."))
                .stream()
                .map(ApplicationResponseDto::new)
                .collect(Collectors.toList());
    }
}
