package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.application.ApplicationQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationQuestionRepository extends JpaRepository<ApplicationQuestion, Integer> {

}
