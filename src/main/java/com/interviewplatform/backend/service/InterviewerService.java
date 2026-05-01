package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.InterviewerProfileRequest;
import com.interviewplatform.backend.entity.InterviewerProfile;
import com.interviewplatform.backend.entity.User;
import com.interviewplatform.backend.repository.InterviewerProfileRepository;
import com.interviewplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
@RequiredArgsConstructor
public class InterviewerService {

    private final InterviewerProfileRepository profileRepository;
    private final UserRepository userRepository;

    public String createProfile(Long userId, InterviewerProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        InterviewerProfile profile = InterviewerProfile.builder()
                .company(request.getCompany())
                .experienceYears(request.getExperienceYears())
                .bio(request.getBio())
                .pricePerHour(request.getPricePerHour())
                .user(user)
                .build();

        profileRepository.save(profile);

        return "Profile created successfully";
    }


    public Page<InterviewerProfile> search(String company, Integer minExp, Double maxPrice, Pageable pageable) {

        Specification<InterviewerProfile> spec = Specification.where(null);

        if (company != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("company")), "%" + company.toLowerCase() + "%"));
        }

        if (minExp != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("experienceYears"), minExp));
        }

        if (maxPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("pricePerHour"), maxPrice));
        }

        return profileRepository.findAll(spec, pageable);
    }
}
