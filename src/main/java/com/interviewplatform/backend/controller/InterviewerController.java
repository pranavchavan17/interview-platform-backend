package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.InterviewerProfileRequest;
import com.interviewplatform.backend.entity.InterviewerProfile;
import com.interviewplatform.backend.service.InterviewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


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


    @GetMapping("/search")
    public Page<InterviewerProfile> search(
            @RequestParam(required = false) String company,
            @RequestParam(required = false) Integer minExp,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return interviewerService.search(company, minExp, maxPrice, PageRequest.of(page, size));
    }
}
