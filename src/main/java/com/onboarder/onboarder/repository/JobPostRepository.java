package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.jobpost.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    // 이름으로 채용 공고를 찾는 메서드
    Optional<JobPost> findByTitle(String title);

    // 회사 이름으로 채용 공고를 찾는 메서드
    Optional<JobPost> findByCompany(String company);

    // 위치로 채용 공고를 찾는 메서드
    Optional<JobPost> findByLocation(String location);

    // 포지션으로 채용 공고를 찾는 메서드
    Optional<JobPost> findByPosition(String position);

    // ID로 채용 공고를 찾는 메서드
    Optional<JobPost> findById(int jobPostingId);
}
