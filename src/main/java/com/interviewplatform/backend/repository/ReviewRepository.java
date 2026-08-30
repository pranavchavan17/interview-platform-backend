package com.interviewplatform.backend.repository;

import com.interviewplatform.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByBookingId(Long bookingId);
    List<Review> findByInterviewerId(Long interviewerId);

    @Query("""
       SELECT AVG(r.rating)
       FROM Review r
       WHERE r.interviewer.id = :interviewerId
       """)
    Double findAverageRatingByInterviewerId(Long interviewerId);
}
