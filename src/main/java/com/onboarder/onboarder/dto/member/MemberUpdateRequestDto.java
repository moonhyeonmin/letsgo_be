package com.onboarder.onboarder.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {

    private String email;
    private String nickname;

    public MemberUpdateRequestDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
