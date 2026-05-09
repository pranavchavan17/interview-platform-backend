package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.BookingRequest;
import com.interviewplatform.backend.entity.Booking;
import com.interviewplatform.backend.entity.TimeSlot;
import com.interviewplatform.backend.entity.User;
import com.interviewplatform.backend.repository.BookingRepository;
import com.interviewplatform.backend.repository.TimeSlotRepository;
import com.interviewplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TimeSlotRepository timeSlotRepository;

    public String bookSlot(BookingRequest request) {

        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        TimeSlot slot = timeSlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.isBooked()) {
            throw new RuntimeException("Slot already booked");
        }

        slot.setBooked(true);

        Booking booking = Booking.builder()
                .student(student)
                .slot(slot)
                .status("CONFIRMED")
                .build();

        bookingRepository.save(booking);

        return "Interview booked successfully";
    }
    public List<Booking> getStudentBookings(Long studentId) {
        return bookingRepository.findByStudentId(studentId);
    }

    public List<Booking> getInterviewerBookings(Long interviewerId) {
        return bookingRepository.findBySlotInterviewerId(interviewerId);
    }
}