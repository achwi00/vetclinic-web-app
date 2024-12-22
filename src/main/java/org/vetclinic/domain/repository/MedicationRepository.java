package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.domain.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>
{
    Medication getMedicationById(Long id);
    boolean existsById(Long id);
}
