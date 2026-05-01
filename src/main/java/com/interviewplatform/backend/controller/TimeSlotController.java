package com.interviewplatform.backend.controller;

import com.interviewplatform.backend.dto.TimeSlotRequest;
import com.interviewplatform.backend.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @PostMapping
    public String addSlot(@RequestParam Long interviewerId,
                          @RequestBody TimeSlotRequest request) {
        return timeSlotService.addSlot(interviewerId, request);
    }
}
