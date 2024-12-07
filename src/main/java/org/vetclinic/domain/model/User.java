package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User //implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role
    {
        VET("vet"), RECEPTIONIST("receptionist"), ADMIN("admin"), CLIENT("client");

        private final String role;

        Role(String role) {
            this.role = role;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(() -> "ROLE_" + role.toUpperCase());
        }
    }
}