package com.onboarder.onboarder.domain.application;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicationQuestion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationQuestion {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;


    @Builder
    public ApplicationQuestion(int id, Application application, String question, String answer) {
        this.id = id;
        this.application = application;
        this.question = question;
        this.answer = answer;
    }
}
