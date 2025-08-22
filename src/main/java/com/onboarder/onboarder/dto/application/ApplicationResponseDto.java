package com.onboarder.onboarder.dto.application;

import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.domain.application.ApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationResponseDto {

    private int id;
    private int jobPostId;
    private ApplicationStatus status;
    private String memo;
    private boolean isResultSuccess;

    public ApplicationResponseDto(Application application) {
        this.id = application.getApplication_id();
        this.jobPostId = application.getJobPost().getJobPostId();
        this.status = application.getStatus();
        this.memo = application.getMemo();
        this.isResultSuccess = application.isResulSuccess();
    }
}
