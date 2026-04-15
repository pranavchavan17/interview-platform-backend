package com.interviewplatform.backend.dto;

import com.interviewplatform.backend.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
