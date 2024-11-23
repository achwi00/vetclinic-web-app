package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vetclinic.model.PetGroup;
import org.vetclinic.model.User;

import java.util.List;

@Repository
public interface PetGroupRepository extends JpaRepository<PetGroup, Long>
{
    List<PetGroup> findAllByOwner(@Param("owner") User owner);
}
