package com.onboarder.onboarder.dto.application;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {
    private int jobPostId;
    private String status;
    private String memo;
    private List<ApplicationQuestionDto> questions;
    private boolean is_result_success;
}
