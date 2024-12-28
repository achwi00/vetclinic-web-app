package org.vetclinic.api.mapper;

import org.vetclinic.api.dto.VisitDto;
import org.vetclinic.domain.model.Visit;

import java.time.LocalDateTime;

public class GeneralVisitMapper
{
    public static VisitDto toDto(Visit visit)
    {
        LocalDateTime start = LocalDateTime.of(visit.getDate(), visit.getStartTime());
        LocalDateTime end = (visit.getEndTime() != null)
                ? LocalDateTime.of(visit.getDate(), visit.getEndTime())
                : start.plusMinutes(30); // Default duration if endTime is not provided

        String title = (visit.getClient() != null)
                ? "Dr. " + visit.getVet().getName() + " " + visit.getVet().getSurname() + ", C: " +  visit.getClient().getName() + " " + visit.getClient().getSurname()
                : "No Client";

        return new VisitDto(
                visit.getId(),
                visit.getDate().toString(),
                start,
                end,
                title
        );
    }
}
