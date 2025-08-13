package com.onboarder.onboarder.repository;

import com.onboarder.onboarder.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
