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

    @ManyToOne
    private Medication medication;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private boolean isMandatory;

    @ManyToOne
    private BasePet basePet;

    @Column(nullable = false)
    private VaccinationStatus vaccinationStatus;

    private enum VaccinationStatus
    {
        UPCOMING("upcoming"), DONE("done"), OVERDUE("overdue");

        VaccinationStatus(String vaccinationStatus)
        {
        }
    }

    @Column(nullable = false)
    private int petsTreated;
}
