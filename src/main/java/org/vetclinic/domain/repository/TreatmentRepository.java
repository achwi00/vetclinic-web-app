package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.Treatment;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>
{
    List<Treatment> getAllByPet_Id(Long basePetId);
}
