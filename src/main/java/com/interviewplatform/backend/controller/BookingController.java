package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.BookingRequest;
import com.interviewplatform.backend.entity.Booking;
import com.interviewplatform.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public String bookSlot(@RequestBody BookingRequest request) {
        return bookingService.bookSlot(request);
    }
    @GetMapping("/student/{studentId}")
    public List<Booking> getStudentBookings(@PathVariable Long studentId) {
        return bookingService.getStudentBookings(studentId);
    }

    @GetMapping("/interviewer/{interviewerId}")
    public List<Booking> getInterviewerBookings(@PathVariable Long interviewerId) {
        return bookingService.getInterviewerBookings(interviewerId);
    }
}