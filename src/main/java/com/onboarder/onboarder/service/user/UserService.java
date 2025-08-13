package com.onboarder.onboarder.service.user;


import com.onboarder.onboarder.domain.user.User;
import com.onboarder.onboarder.dto.member.MemberCreateRequestDto;
import com.onboarder.onboarder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


/**
     * 회원 가입 메서드
     *
     * @param requestDto 회원 가입 요청 DTO
     */
    @Transactional
    public void signUp(MemberCreateRequestDto requestDto) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 닉네임 중복 확인
        if (userRepository.existsByNickname(requestDto.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // DTO를 User 엔티티로 변환하여 저장
        User newUser = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword())) // 비밀번호 암호화
                .nickname(requestDto.getNickname())
                .build();

        userRepository.save(newUser);
    }
}
