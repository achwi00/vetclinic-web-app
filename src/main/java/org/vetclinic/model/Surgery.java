package org.vetclinic.model;

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

    private String description;

    @ManyToOne
    private BasePet basePet;

    private Instant date;

    @Column(nullable = false)
    private int petsOperated;
}
