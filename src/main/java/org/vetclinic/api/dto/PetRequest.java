package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PetRequest
{
    private String email;
    private String petName;
    private String breed;
    private String type;
    private LocalDate birthDate;
    private String sex;
}
