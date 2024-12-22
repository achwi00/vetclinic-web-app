package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TreatmentDto
{
    private Long visitId;
    private String medicationName;
    private int numOfPets;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}