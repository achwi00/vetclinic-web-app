package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Treatment
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long petId;//id of the pet treated

    private Long medicationId;//id of the Medication

    @Column(nullable = false)
    private String description;
}
