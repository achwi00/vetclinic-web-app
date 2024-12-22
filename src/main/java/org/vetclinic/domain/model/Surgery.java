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
public class Surgery
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Visit visit;

    private String description;

    @ManyToOne
    private BasePet basePet;

    private LocalDate date;

    @Column(nullable = false)
    private int petsOperated;
}
