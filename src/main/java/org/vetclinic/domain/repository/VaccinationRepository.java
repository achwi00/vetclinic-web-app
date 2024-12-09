package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.BasePet;
import org.vetclinic.domain.model.Vaccination;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long>
{
    //List<Vaccination> getAllByBasePetAndVaccinationStatus(BasePet basePet, Vaccination.VaccinationStatus status);
    List<Vaccination> getAllByBasePet_IdAndVaccinationStatus(Long basePetId, Vaccination.VaccinationStatus status);
}
