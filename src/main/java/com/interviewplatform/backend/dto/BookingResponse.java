package com.interviewplatform.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private Long bookingId;
    private String status;
    private Long slotId;
    private Long studentId;
}