package com.interviewplatform.backend.dto;

import lombok.Data;

@Data
public class InterviewerProfileRequest {

    private String company;
    private Integer experienceYears;
    private String bio;
    private Double pricePerHour;
}
