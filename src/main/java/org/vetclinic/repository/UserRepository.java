package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
}
