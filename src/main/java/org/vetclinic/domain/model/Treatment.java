package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Medication medication;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int petsTreated;
}
