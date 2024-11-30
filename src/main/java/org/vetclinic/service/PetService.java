package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.User;
import org.vetclinic.domain.repository.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PetService
{
    private PetRepository petRepository;
    private UserService userService;

    public List<Pet> getPetsForUser(String email){
        try{
            User owner = userService.getUserByEmail(email);
            if(owner != null) {
                return petRepository.findAllByOwner(owner);
            }
        }catch (Exception e){
            log.info("Error fetching pets for user");
        }
        return List.of();
    }

    public Pet getPetByOwnerAndName(User owner, String name){
        Pet pet = null;
        try{
            Optional<Pet> optionalPet = petRepository.findPetByOwnerAndName(owner, name);
            if(optionalPet.isPresent()){
                pet = optionalPet.get();
            }
        }catch (Exception e){
            log.info("Pet not found for provided user and name.");
        }
        return pet;
    }
}
