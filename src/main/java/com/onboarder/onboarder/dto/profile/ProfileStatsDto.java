package com.onboarder.onboarder.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileStatsDto {
    private long totalApplications;
    private long documentsPassedCount;
    private long interviewsInProgressCount;
    private long bookmarksCount;
}
