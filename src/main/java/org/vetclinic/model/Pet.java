package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
public class Pet extends BasePet{

    public Pet(String name, Type type, String breed, Instant birthDate, User owner){
        super(name, type, breed, owner);
        this.birthDate = birthDate;
        //this.owner = owner;

    }
    private Instant birthDate;

//    @ManyToOne
//    private User owner;

}
