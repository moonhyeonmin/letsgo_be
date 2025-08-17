package com.onboarder.onboarder.dto.jobpost;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class JobPostCreateRequestDto {

    private String title;
    private String content;
    private String company;
    private String location;
    private String position;
    private String salary;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;
    private String requirements;
    private String source_url;
}
