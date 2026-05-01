package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.InterviewerProfileRequest;
import com.interviewplatform.backend.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interviewer")
@RequiredArgsConstructor
public class InterviewerController {

    private final InterviewerService interviewerService;

    @PostMapping("/profile")
    public String createProfile(@RequestParam Long userId,
                                @RequestBody InterviewerProfileRequest request) {
        return interviewerService.createProfile(userId, request);
    }
}
