package com.onboarder.onboarder.dto.member;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberCreateRequestDto {

    private String email;
    private String password;
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
    private boolean completed;



    public MemberCreateRequestDto(boolean completed, String email, String password, String nickname, String name, String phone, String location, String experience, String position, String bio, List<String> skills, String education, String company) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.experience = experience;
        this.position = position;
        this.bio = bio;
        this.skills = skills;
        this.education = education;
        this.company = company;
        this.completed = completed;
    }

}
