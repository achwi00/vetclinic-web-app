package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vetclinic.domain.model.PetGroup;
import org.vetclinic.domain.model.User;

import java.util.List;

@Repository
public interface PetGroupRepository extends JpaRepository<PetGroup, Long>
{
    List<PetGroup> findAllByOwner(@Param("owner") User owner);
}
