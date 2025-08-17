package com.onboarder.onboarder.dto.jobpost;

import com.onboarder.onboarder.domain.jobpost.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobPostResponseDto {

    private int id;
    private String title;
    private String company;
    private String location;
    private String position;
    private String content;

    public JobPostResponseDto(JobPost jobPost) {
        this.id = jobPost.getJob_posting_id();
        this.title = jobPost.getTitle();
        this.content = jobPost.getContent();
        this.company = jobPost.getCompany();
        this.location = jobPost.getLocation();
        this.position = jobPost.getPosition();
    }
}
