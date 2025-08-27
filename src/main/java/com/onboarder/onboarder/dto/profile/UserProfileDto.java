package com.onboarder.onboarder.dto.profile;

import com.onboarder.onboarder.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserProfileDto {

    private int user_id;
    private String email;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserProfileDto(User user) {
        this.user_id = user.getUser_id();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.location = user.getLocation();
        this.experience = user.getExperience();
        this.position = user.getPosition();
        this.bio = user.getBio();
        this.skills = new ArrayList<>(user.getSkills());
        this.education = user.getEducation();
        this.company = user.getCompany();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
