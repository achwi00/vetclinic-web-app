package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookVisitRequest
{
    private Long visitId;
    private String email;
    private String petName;
}
