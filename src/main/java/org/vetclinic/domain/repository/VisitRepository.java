package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.User;
import org.vetclinic.domain.model.Visit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long>
{
    //List<Visit> findAllByBasePetAndClient(BasePet pet, User Client);

    List<Visit> findAllByDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, Visit.VisitStatus status);
    List<Visit> findAllByClientAndStatus(User client, Visit.VisitStatus status);
    List<Visit> findAllByVetAndDateAndEndTimeAfter(User vet, LocalDate date, LocalTime endTime);
    Optional<Visit> getVisitById(Long id);
    List<Visit> findAllByVetAndStatus(User vet, Visit.VisitStatus status);
    List<Visit> findAllByStatus(Visit.VisitStatus status);
}
