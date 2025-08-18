package com.onboarder.onboarder.controller;

import com.onboarder.onboarder.dto.jobpost.JobPostCreateRequestDto;
import com.onboarder.onboarder.dto.jobpost.JobPostResponseDto;
import com.onboarder.onboarder.service.jobpost.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    /**
     * GET /api/jobs - 전체 공고 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<JobPostResponseDto>> getJobPosts() {
        List<JobPostResponseDto> jobPosts = jobPostService.getJobPosts();
        return ResponseEntity.ok(jobPosts);
    }

    /**
     * GET api/jobs/{id} - ID로 공고 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPostResponseDto> getJobPostById(@PathVariable int id) {
        JobPostResponseDto jobPost = jobPostService.getJobPostById(id);
        return ResponseEntity.ok(jobPost);
    }

    /**
     * Post api/jobs - 새로운 공고 등록
     */
    @PostMapping
    public ResponseEntity<JobPostResponseDto> createJobPost(@RequestBody JobPostCreateRequestDto requestDto) {
        JobPostResponseDto createdJobPost = jobPostService.createJobPost(requestDto);
        return new ResponseEntity<>(createdJobPost, HttpStatus.CREATED);
    }

    /**
     * GET api/jobs/company/{company} - 회사 이름으로 공고 조회
     */
    @GetMapping("/company/{company}")
    public ResponseEntity<List<JobPostResponseDto>> getJobPostByCompany(@PathVariable String company) {
        List<JobPostResponseDto> jobPosts = jobPostService.getJobPostsByCompany(company);
        return ResponseEntity.ok(jobPosts);
    }

    /**
     * GET api/jobs/title/{title} - 제목으로 공고 조회
     */
    @GetMapping("/title/{title}")
    public ResponseEntity<List<JobPostResponseDto>> getJobPostByTitle(@PathVariable String title) {
        List<JobPostResponseDto> jobPosts = jobPostService.getJobPostByTitle(title);

        return jobPosts.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(jobPosts);
    }

    /**
     * GET api/jobs/location/{location} - 위치로 공고 조회
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobPostResponseDto>> getJobPostsByLocation(@PathVariable String location) {
        List<JobPostResponseDto> jobPosts = jobPostService.getJobPostsByLocation(location);

        return jobPosts.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(jobPosts);
    }

    /**
     * GET api/jobs/position/{position} - 포지션으로 공고 조회
     */
    @GetMapping("/position/{position}")
    public ResponseEntity<List<JobPostResponseDto>> getJobPostsByPosition(@PathVariable String position) {
        List<JobPostResponseDto> jobPosts = jobPostService.getJobPostsByPosition(position);
        return jobPosts.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(jobPosts);
    }
}
