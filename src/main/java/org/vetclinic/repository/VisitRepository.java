package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.model.BasePet;
import org.vetclinic.model.User;
import org.vetclinic.model.Visit;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long>
{
    //List<Visit> findAllByBasePetAndClient(BasePet pet, User Client);

    List<Visit> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
