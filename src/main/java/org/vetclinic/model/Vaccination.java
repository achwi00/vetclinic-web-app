package org.vetclinic.model;

import java.time.Instant;

public class Vaccination
{
    private Long id;
    private Long petId;
    private Long vaccineId;
    private Instant date;
    private boolean isMandatory;
    //VaccinationStatus <UPCOMING, DONE, OVERDUE>
    //illnessId? List of Illness?
}
