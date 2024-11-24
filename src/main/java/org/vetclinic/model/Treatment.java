package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Treatment
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private BasePet pet;

    @Column(nullable = false)
    private Instant startDate;
    private Instant endDate;

    @ManyToOne
    private Medication medication;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int petsTreated;
}
