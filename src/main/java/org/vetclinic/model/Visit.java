package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Visit
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Instant date;

    private Instant startTime;

    private Instant endTime;//for group visit mostly

    @ManyToOne
    private User client;

    @ManyToOne
    private User vet;

    @ManyToOne
    private BasePet basePet;

//    @ManyToOne
//    private BasePet petGroupId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

    private enum VisitStatus{
        FREE("free"), PENDING("pending"), UPCOMING("upcoming"), CANCELLED("cancelled"), OUTDATED("outdated");

        VisitStatus(String status){}
    }
}