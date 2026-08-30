package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.ReviewRequest;
import com.interviewplatform.backend.entity.*;
import com.interviewplatform.backend.repository.BookingRepository;
import com.interviewplatform.backend.repository.ReviewRepository;
import com.interviewplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.interviewplatform.backend.dto.ReviewResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public String addReview(ReviewRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("You can review only your own booking");
        }

        if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new RuntimeException(
                    "Review allowed only after interview completion"
            );
        }

        if (reviewRepository.findByBookingId(booking.getId()).isPresent()) {
            throw new RuntimeException(
                    "Review already submitted for this booking"
            );
        }

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException(
                    "Rating must be between 1 and 5"
            );
        }

        Review review = Review.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .createdAt(LocalDateTime.now())
                .student(student)
                .interviewer(booking.getSlot().getInterviewer())
                .booking(booking)
                .build();

        reviewRepository.save(review);

        return "Review submitted successfully";
    }
    public List<ReviewResponse> getInterviewerReviews(Long interviewerId) {

        return reviewRepository.findByInterviewerId(interviewerId)
                .stream()
                .map(review -> ReviewResponse.builder()
                        .rating(review.getRating())
                        .comment(review.getComment())
                        .studentName(review.getStudent().getName())
                        .build())
                .collect(Collectors.toList());
    }
    public Double getAverageRating(Long interviewerId) {

        Double average =
                reviewRepository.findAverageRatingByInterviewerId(interviewerId);

        return average != null ? average : 0.0;
    }
}
