package com.interviewplatform.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interviewer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private Integer experienceYears;
    private String bio;
    private Double pricePerHour;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
