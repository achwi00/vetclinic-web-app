package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
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

    private Instant date;

    @Column(nullable = false)
    private int petsOperated;
}
