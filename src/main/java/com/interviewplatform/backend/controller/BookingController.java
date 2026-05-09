package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.BookingRequest;
import com.interviewplatform.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public String bookSlot(@RequestBody BookingRequest request) {
        return bookingService.bookSlot(request);
    }
}