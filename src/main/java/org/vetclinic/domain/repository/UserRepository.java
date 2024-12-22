package org.vetclinic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vetclinic.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u.email FROM User u WHERE u.role = :role")
    List<String> findAllEmailByRole(User.Role role);
}
