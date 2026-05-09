package com.interviewplatform.backend.dto;

import lombok.Data;

@Data
public class BookingRequest {

    private Long studentId;
    private Long slotId;
}