package org.vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vetclinic.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findAll();
    Optional<User> findByEmail(String email);
}
