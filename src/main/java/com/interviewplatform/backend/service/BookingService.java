package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.BookingRequest;
import com.interviewplatform.backend.entity.Booking;
import com.interviewplatform.backend.entity.BookingStatus;
import com.interviewplatform.backend.entity.TimeSlot;
import com.interviewplatform.backend.entity.User;
import com.interviewplatform.backend.repository.BookingRepository;
import com.interviewplatform.backend.repository.TimeSlotRepository;
import com.interviewplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import com.interviewplatform.backend.dto.BookingResponse;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TimeSlotRepository timeSlotRepository;

    public String bookSlot(BookingRequest request) {

        System.out.println("BOOK SLOT API HIT");
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User student = userRepository.findByEmail(email)
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
                .status(BookingStatus.PENDING)
                .build();

        bookingRepository.save(booking);

        return "Interview booked successfully";
    }

    public List<BookingResponse> getStudentBookings(Long studentId) {

        return bookingRepository.findByStudentId(studentId)
                .stream()
                .map(booking -> BookingResponse.builder()
                        .bookingId(booking.getId())
                        .status(booking.getStatus().name())
                        .slotId(booking.getSlot().getId())
                        .studentId(booking.getStudent().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getInterviewerBookings(Long interviewerId) {

        return bookingRepository.findBySlotInterviewerId(interviewerId)
                .stream()
                .map(booking -> BookingResponse.builder()
                        .bookingId(booking.getId())
                        .status(booking.getStatus().name())
                        .slotId(booking.getSlot().getId())
                        .studentId(booking.getStudent().getId())
                        .build())
                .collect(Collectors.toList());
    }

    public String cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);

        TimeSlot slot = booking.getSlot();
        slot.setBooked(false);

        bookingRepository.save(booking);

        return "Booking cancelled successfully";
    }
    public String confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CONFIRMED);

        bookingRepository.save(booking);

        return "Booking confirmed successfully";
    }
    public String completeBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.COMPLETED);

        bookingRepository.save(booking);

        return "Interview completed successfully";
    }
}