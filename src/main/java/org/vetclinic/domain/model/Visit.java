package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Visit
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    private LocalTime endTime;//for group visit mostly

    @ManyToOne
    private User client;

    @ManyToOne
    private User vet;

    @ManyToOne
    private BasePet basePet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

    public enum VisitStatus{
        FREE("free"), PENDING("pending"), UPCOMING("upcoming"), CANCELLED("cancelled"), OUTDATED("outdated"), COMPLETED("completed");

        VisitStatus(String status){}
    }
}