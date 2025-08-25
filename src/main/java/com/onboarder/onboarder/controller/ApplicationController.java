package com.onboarder.onboarder.controller;


import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.dto.application.ApplicationQuestionDto;
import com.onboarder.onboarder.dto.application.ApplicationRequestDto;
import com.onboarder.onboarder.dto.application.ApplicationResponseDto;
import com.onboarder.onboarder.service.application.ApplicationService;
import com.onboarder.onboarder.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * 유저가 채용 공고에 지원하는 API
     *
     * @param userDetails 인증된 사용자 정보
     * @param requestDto  지원 요청 DTO
     * @return HTTP 상태 코드 201 Created
     */
    @PostMapping
    public ResponseEntity<ApplicationResponseDto> applyToJobPost(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ApplicationRequestDto requestDto) {

        String userEmail = userDetails.getUsername();
        ApplicationResponseDto applicationResponseDto = applicationService.applyToJobPost(requestDto, userEmail);

        return new ResponseEntity<>(applicationResponseDto, HttpStatus.CREATED);
    }

    /**
     * 유저가 지원한 채용 공고를 조회하는 API
     * * @param userDetails 인증된 사용자 정보
     * @return HTTP 상태 코드 200 OK와 지원한 채용 공고 목록
     */

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponseDto>> getMyApplications(
            @AuthenticationPrincipal UserDetails userDetails) {


        String userEmail = userDetails.getUsername();
        return ResponseEntity.ok(applicationService.getApplicationByEmail(userEmail));
    }

    /**
     * 유저가 지원한 채용 공고를 상태별로 조회하는 API
     *
     * @param status  지원 상태
     * @return HTTP 상태 코드 200 OK와 상태별 지원한 채용 공고 목록
     */
    @GetMapping("/my/status")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsByUserAndStatus(
            @RequestParam String status,
            @AuthenticationPrincipal UserDetails userDetails) {

        String userEmail = userDetails.getUsername();
        return ResponseEntity.ok(applicationService.getApplicationsByEmailAndStatus(userEmail, status));
    }

    /**
     * 유저가 지원한 채용 공고를 채용 공고 ID로 조회하는 API
     *
     * @param userId    유저 ID
     * @param jobPostId 채용 공고 ID
     * @return HTTP 상태 코드 200 OK와 채용 공고 목록
     */
    @GetMapping("/user/{userId}/jobpost/{jobPostId}")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsByUserAndJobPostId(
            @PathVariable int userId,
            @PathVariable int jobPostId,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(applicationService.getApplicationsByUserAndJobPostId(userId, jobPostId));
    }
}
