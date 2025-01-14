package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vetclinic.domain.model.PetGroup;
import org.vetclinic.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetGroupRepository extends JpaRepository<PetGroup, Long>
{
    List<PetGroup> findAllByOwner(@Param("owner") User owner);
    boolean existsByOwnerAndName(User owner, String name);
    Optional<PetGroup> findPetGroupByOwnerAndName(User owner, String name);
}
