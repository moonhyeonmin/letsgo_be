package com.onboarder.onboarder.domain.application;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    APPLIED("서류 지원"),
    INTERVIEW("면접 진행"),
    SUCCESS("최종 합격"),
    FAIL("불합격");

    private final String description;

    ApplicationStatus(String description) {
        this.description = description;
    }
}
