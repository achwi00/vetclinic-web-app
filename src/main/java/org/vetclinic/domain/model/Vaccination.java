package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
public class Vaccination
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Medication medication;

    @ManyToOne
    private Visit visit;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean isMandatory;

    @ManyToOne
    private BasePet basePet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaccinationStatus vaccinationStatus;

    public enum VaccinationStatus
    {
        UPCOMING("upcoming"), DONE("done"), OVERDUE("overdue");

        VaccinationStatus(String vaccinationStatus)
        {
        }
    }

    @Column(nullable = false)
    private int petsTreated;

}
