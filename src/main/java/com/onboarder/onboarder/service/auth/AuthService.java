package com.onboarder.onboarder.service.auth;

import com.onboarder.onboarder.config.jwt.JwtTokenProvider;
import com.onboarder.onboarder.domain.user.User;
import com.onboarder.onboarder.dto.member.LoginRequestDto;
import com.onboarder.onboarder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인 메서드
     *
     * @param loginRequestDto 로그인 요청 DTO
     * @return JWT 토큰
     */

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto) {

        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(loginRequestDto.getEmail());

        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
        }

        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호가 일치하면 JWT 토큰 생성
        return jwtTokenProvider.createToken(user.getEmail());
    }
}
