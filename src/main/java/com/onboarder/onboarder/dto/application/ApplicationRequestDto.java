package com.onboarder.onboarder.dto.application;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationRequestDto {
    private int jobPostId;
    private String status;
}
