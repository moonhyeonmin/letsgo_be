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
    private LocalDateTime deadline;

    public JobPostResponseDto(JobPost jobPost) {
        this.id = jobPost.getJobPostId();
        this.title = jobPost.getTitle();
        this.content = jobPost.getContent();
        this.company = jobPost.getCompany().getName();
        this.location = jobPost.getLocation();
        this.position = jobPost.getPosition();
        this.deadline = jobPost.getDeadline();
    }
}
