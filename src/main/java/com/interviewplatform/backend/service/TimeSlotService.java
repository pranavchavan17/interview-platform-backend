package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.TimeSlotRequest;
import com.interviewplatform.backend.entity.InterviewerProfile;
import com.interviewplatform.backend.entity.TimeSlot;
import com.interviewplatform.backend.repository.InterviewerProfileRepository;
import com.interviewplatform.backend.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final InterviewerProfileRepository profileRepository;

    public String addSlot(Long interviewerId, TimeSlotRequest request) {

        InterviewerProfile interviewer = profileRepository.findById(interviewerId)
                .orElseThrow(() -> new RuntimeException("Interviewer not found"));

        TimeSlot slot = TimeSlot.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .interviewer(interviewer)
                .build();

        timeSlotRepository.save(slot);

        return "Slot added successfully";
    }
}
