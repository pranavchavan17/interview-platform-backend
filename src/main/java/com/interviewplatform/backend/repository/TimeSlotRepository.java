package com.interviewplatform.backend.repository;

import com.interviewplatform.backend.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    List<TimeSlot> findByInterviewerIdAndIsBookedFalse(Long interviewerId);
}
