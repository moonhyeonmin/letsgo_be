package com.onboarder.onboarder.dto.jobpost;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class JobPostCreateRequestDto {

    private String title;
    private String content;
    private String companyName;
    private String location;
    private String position;
    private String company_size;
    private String industry;
    private String salary;
    private LocalDateTime deadline;
    private String requirements;
}
