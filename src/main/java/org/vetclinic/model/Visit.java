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
    //endTime-- maybe for GroupVisit

    private Long petId;//or petGroupId?

    @Column(nullable = false)
    private Long clientId;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    private enum VisitStatus{
        FREE("free"), PENDING("pending"), UPCOMING("upcoming"), CANCELLED("cancelled"), OUTDATED("outdated");

        VisitStatus(String status){}
    }
}