package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CustomVisitDto
{
    private String userEmail;
    private String petName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String vetEmail;
}
