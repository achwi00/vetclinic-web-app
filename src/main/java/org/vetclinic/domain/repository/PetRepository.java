package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>
{
    List<Pet> findAllByOwner(@Param("owner") User owner);
    Optional<Pet> findPetByOwnerAndName(User owner, String name);
    boolean existsByOwnerAndName(User owner, String name);
}
