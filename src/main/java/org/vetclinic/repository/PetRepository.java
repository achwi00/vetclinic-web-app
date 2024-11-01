package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vetclinic.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{
}
