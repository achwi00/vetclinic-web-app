package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long>
{
    //List<Visit> findAllByBasePetAndClient(BasePet pet, User Client);

    List<Visit> findAllByDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, Visit.VisitStatus status);
}
