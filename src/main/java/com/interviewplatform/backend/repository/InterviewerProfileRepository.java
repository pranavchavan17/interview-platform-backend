package com.interviewplatform.backend.repository;

import com.interviewplatform.backend.entity.InterviewerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewerProfileRepository
        extends JpaRepository<InterviewerProfile, Long>,
        JpaSpecificationExecutor<InterviewerProfile> {
}
