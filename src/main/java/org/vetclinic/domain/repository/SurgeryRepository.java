package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.Surgery;
import org.vetclinic.domain.model.Visit;

import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long>
{
    List<Surgery> getAllByBasePet_IdAndVisit_Status(Long basePetId, Visit.VisitStatus status);

}
