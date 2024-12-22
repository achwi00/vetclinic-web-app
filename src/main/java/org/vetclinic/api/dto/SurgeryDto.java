package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SurgeryDto
{
    private Long visitId;
    private int numOfPets;
    private String description;
}
