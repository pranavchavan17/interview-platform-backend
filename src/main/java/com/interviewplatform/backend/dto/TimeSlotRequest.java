package com.interviewplatform.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlotRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
