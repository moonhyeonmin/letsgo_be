package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.application.Application;
import com.onboarder.onboarder.domain.jobpost.JobPost;
import com.onboarder.onboarder.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // 유저가 지원한 채용 공고를 전부 조회
    Optional<List<Application>> findAllByUser(User user);

    // 유저가 지원한 채용 공고를 상태별로 조회
    Optional<List<Application>> findAllByUserAndStatus(User user, String status);

    // 유저가 지원한 채용 공고를 채용 공고 ID로 조회
    Optional<List<Application>> findAllByUserAndJobPost(User user, JobPost jobPost);
}
