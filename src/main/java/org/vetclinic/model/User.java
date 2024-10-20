package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private enum Role{
        VET("vet"), RECEPTIONIST("receptionist"), ADMIN("admin"), CLIENT("client");

        Role(String role){}
    }
}