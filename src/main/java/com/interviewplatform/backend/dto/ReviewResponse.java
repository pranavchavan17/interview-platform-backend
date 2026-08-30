package com.interviewplatform.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {

    private Integer rating;
    private String comment;
    private String studentName;
}