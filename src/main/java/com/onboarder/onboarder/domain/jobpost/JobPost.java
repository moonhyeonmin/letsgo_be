package com.onboarder.onboarder.domain.jobpost;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * JobPost 엔티티
 * 온보더의 채용 공고 정보를 관리
 */

@Entity
@Table(name = "JobPosting")
@Getter
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int job_posting_id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String requirements;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String salary;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String source_url;

    @Builder
    public JobPost(int job_posting_id, String title, String position, String requirements,
                   String location, String salary, LocalDateTime deadline, String source_url, String company, String content) {
        this.company = company;
        this.content = content;
        this.job_posting_id = job_posting_id;
        this.title = title;
        this.position = position;
        this.requirements = requirements;
        this.location = location;
        this.salary = salary;
        this.deadline = deadline;
        this.source_url = source_url;
    }
}
