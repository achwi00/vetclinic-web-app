package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class PetGroup extends BasePet{

    PetGroup(String name, Type type, String breed, int size, User owner){
        super(name, type, breed, owner);
        this.size = size;
    }

    @Column(nullable = false)
    private int size;

}