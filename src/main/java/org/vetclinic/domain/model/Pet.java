package org.vetclinic.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Pet extends BasePet{

    public Pet(String name, Type type, String breed, LocalDate birthDate, User owner, Sex sex){
        super(name, type, breed, owner);
        this.birthDate = birthDate;
        this.sex = sex;
    }
    private LocalDate birthDate;

    public enum Sex{
        MALE("male"), FEMALE("female"), UNKNOWN("unknown");

        Sex(String sex){}
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
}
