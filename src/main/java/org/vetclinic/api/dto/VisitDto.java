package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VisitDto
{
    private Long visitId;
    private String date;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
}
