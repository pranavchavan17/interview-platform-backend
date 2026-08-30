package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.ReviewRequest;
import com.interviewplatform.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.interviewplatform.backend.dto.ReviewResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public String addReview(@RequestBody ReviewRequest request) {
        return reviewService.addReview(request);
    }
    @GetMapping("/interviewer/{interviewerId}")
    public List<ReviewResponse> getInterviewerReviews(
            @PathVariable Long interviewerId) {

        return reviewService.getInterviewerReviews(interviewerId);
    }
    @GetMapping("/interviewer/{interviewerId}/average-rating")
    public Double getAverageRating(
            @PathVariable Long interviewerId) {

        return reviewService.getAverageRating(interviewerId);
    }
}