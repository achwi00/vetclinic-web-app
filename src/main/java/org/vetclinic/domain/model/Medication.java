package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medication
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
