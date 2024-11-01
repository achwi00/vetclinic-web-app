package org.vetclinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class BasePet
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    public BasePet(String name, Type type, String breed, User owner)
    {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.owner = owner;
    }

    public enum Type{
        CAT("cat"), DOG("dog"), RABBIT("rabbit"), RODENT("rodent"), FARM_ANIMAL("farm_animal");

        Type(String type){}
    }

    @Column(nullable = false)
    private String breed;

    @ManyToOne
    private User owner;
}
