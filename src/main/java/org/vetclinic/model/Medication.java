package org.vetclinic.model;

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
    //dose?
    @Column(nullable = false)
    private String batch;
}
