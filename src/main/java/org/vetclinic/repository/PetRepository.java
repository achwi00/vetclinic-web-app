package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vetclinic.model.Pet;
import org.vetclinic.model.User;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{
    List<Pet> findAllByOwner(@Param("owner") User owner);
}
