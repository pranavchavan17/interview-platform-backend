package com.interviewplatform.backend.repository;

import com.interviewplatform.backend.entity.InterviewerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewerProfileRepository extends JpaRepository<InterviewerProfile, Long> {
}
