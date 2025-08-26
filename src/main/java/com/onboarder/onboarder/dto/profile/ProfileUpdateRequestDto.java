package com.onboarder.onboarder.dto.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProfileUpdateRequestDto {
    private String nickname;
    private String name;
    private String phone;
    private String location;
    private String experience;
    private String position;
    private String bio;
    private List<String> skills;
    private String education;
    private String company;
}
