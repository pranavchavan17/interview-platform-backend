package com.interviewplatform.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @OneToOne
    @JoinColumn(name = "slot_id", unique = true)
    private TimeSlot slot;
}