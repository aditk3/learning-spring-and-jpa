package com.aditkapoor.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    private String status;

    @ManyToOne
    @JoinColumn(nullable = false)  // Patient is required, therefore NOT nullable
    @ToString.Exclude
    private Patient patient;

//    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)  // Doctor is required, therefore NOT nullable
    private Doctor doctor;
}
