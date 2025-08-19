package com.onboarder.onboarder.service.jobpost;


import com.onboarder.onboarder.domain.jobpost.JobPost;
import com.onboarder.onboarder.dto.jobpost.JobPostCreateRequestDto;
import com.onboarder.onboarder.dto.jobpost.JobPostResponseDto;
import com.onboarder.onboarder.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    @Transactional(readOnly = true)
    public List<JobPostResponseDto> getJobPosts() {
        // 모든 채용 공고를 조회하여 JobPostResponseDto 리스트로 변환
        return jobPostRepository.findAll().stream()
                .map(JobPostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public JobPostResponseDto getJobPostById(int jobPostId) {
        // ID로 채용공고를 조회
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 채용 공고가 존재하지 않습니다."));

        return new JobPostResponseDto(jobPost);
    }

    @Transactional(readOnly = true)
    public List<JobPostResponseDto> getJobPostsByCompany(String company) {
        // 회사 이름으로 채용 공고를 조회
        return jobPostRepository.findByCompany(company)
                .stream()
                .map(JobPostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobPostResponseDto> getJobPostByTitle(String title) {
        return jobPostRepository.findByTitle(title)
                .stream()
                .map(JobPostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobPostResponseDto> getJobPostsByLocation(String location) {
        // 위치로 채용 공고를 조회
        return jobPostRepository.findByLocation(location)
                .stream()
                .map(JobPostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<JobPostResponseDto> getJobPostsByPosition(String position) {
        // 포지션으로 채용 공고를 조회
        return jobPostRepository.findByPosition(position)
                .stream()
                .map(JobPostResponseDto::new)
                .toList();
    }

    @Transactional
    public JobPostResponseDto createJobPost(JobPostCreateRequestDto requestDto) {
        // 채용 공고 생성
        JobPost jobPost = JobPost.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .company(requestDto.getCompany())
                .location(requestDto.getLocation())
                .position(requestDto.getPosition())
                .salary(requestDto.getSalary())
                .deadline(requestDto.getDeadline())
                .requirements(requestDto.getRequirements())
                .source_url(requestDto.getSource_url())
                .build();

        JobPost savedJobPost = jobPostRepository.save(jobPost);
        return new JobPostResponseDto(savedJobPost);
    }
}
