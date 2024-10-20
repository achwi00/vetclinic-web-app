package org.vetclinic.model;

import java.time.Instant;

public class Visit
{
    private Long id;
    private Instant date;
    //startTime
    //endTime
    //Status <PENDING, CANCELLED, FREE, UPCOMING, OUTDATED>
    private Long petId;//or petGroupId?
}
