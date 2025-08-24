package com.onboarder.onboarder.dto.application;

import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.domain.application.ApplicationStatus;
import com.onboarder.onboarder.dto.jobpost.JobPostResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ApplicationResponseDto {

    private int id;
    private JobPostResponseDto jobPost;
    private ApplicationStatus status;
    private String memo;
    private LocalDateTime applicationDate;

    public ApplicationResponseDto(Application application) {
        this.id = application.getApplication_id();
        this.jobPost = new JobPostResponseDto(application.getJobPost());
        this.status = application.getStatus();
        this.memo = application.getMemo();
        this.applicationDate = application.getApplicationDate();
    }
}
