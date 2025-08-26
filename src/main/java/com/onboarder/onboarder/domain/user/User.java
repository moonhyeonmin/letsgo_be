package com.onboarder.onboarder.domain.user;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User 엔티티
 * 온보더의 사용자 정보를 관리
 */

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String name;
    private String phone;
    private String location;
    private String experience;
    private String position;
    private String bio;
    private Boolean completed = false;


    @ElementCollection
    @CollectionTable(name = "user_skills", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    private String education;
    private String company;

    @Builder
    public User(int user_id, String email, String password, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt,
                Boolean completed, String name, String phone, String location, String education, String experience, String position, String bio, List<String> skills, String company) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.experience = experience;
        this.position = position;
        this.bio = bio;
        this.skills = skills;
        this.education = education;
        this.company = company;

    }

    public void updateProfile(String nickname, String name, String phone, String location, String experience, String position, String bio, List<String> skills, String education, String company) {
        this.nickname = nickname != null ? nickname : this.nickname;
        this.name = name != null ? name : this.name;
        this.phone = phone != null ? phone : this.phone;
        this.location = location != null ? location : this.location;
        this.experience = experience != null ? experience : this.experience;
        this.position = position != null ? position : this.position;
        this.bio = bio != null ? bio : this.bio;
        this.skills = skills != null ? skills : this.skills;
        this.education = education != null ? education : this.education;
        this.company = company != null ? company : this.company;
    }
}
