package com.onboarder.onboarder.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequestDto {

    private String email;
    private String password;
    private String nickname;

}
