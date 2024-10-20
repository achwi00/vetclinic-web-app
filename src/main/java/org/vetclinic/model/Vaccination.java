package org.vetclinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Vaccination
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long petId;
    private Long vaccineId;
    private Instant date;
    private boolean isMandatory;
    //VaccinationStatus <UPCOMING, DONE, OVERDUE>
    //illnessId? List of Illness?
}
