package com.onboarder.onboarder.dto.application;

import com.onboarder.onboarder.domain.application.ApplicationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {
    private int jobPostId;
    private ApplicationStatus status;
    private String memo;
    private List<ApplicationQuestionDto> questions;
    private boolean isResulSuccess;
}
