package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VaccinationDto
{
    private Long visitId;
    private String medicationName;
    private int numOfPets;
}
