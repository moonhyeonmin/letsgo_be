package com.onboarder.onboarder.domain.company;


import com.onboarder.onboarder.domain.jobpost.JobPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int id;

    @Column(nullable = false, length = 100, unique = true, name = "name")
    private String name;

    @Column(nullable = false, name = "size")
    private String size;

    @Column(nullable = false)
    private String industry;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "company")
    private List<JobPost> jobPosts;
}
