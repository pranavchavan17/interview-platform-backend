package com.interviewplatform.backend.dto;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long bookingId;
    private Integer rating;
    private String comment;
}
