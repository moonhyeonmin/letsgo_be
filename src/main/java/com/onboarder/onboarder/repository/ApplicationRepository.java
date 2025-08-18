package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // 유저가 지원한 채용 공고를 전부 조회
    List<Application> findAllByUserId(int userId);

    // 유저가 지원한 채용 공고를 상태별로 조회
    List<Application> findAllByUserIdAndStatus(int userId, String status);

    // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
    List<Application> findAllByUserIdAndJobPostJobPostingId(int userId, int jobPostId);
}
