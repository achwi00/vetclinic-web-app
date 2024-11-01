package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Vaccination
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long petId;
    private Long vaccineId;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private boolean isMandatory;

    @Column(nullable = false)
    private VaccinationStatus vaccinationStatus;

    private enum VaccinationStatus
    {
        UPCOMING("upcoming"), DONE("done"), OVERDUE("overdue");

        VaccinationStatus(String vaccinationStatus)
        {
        }
    }
    //illnessId? List of Illness?
}
