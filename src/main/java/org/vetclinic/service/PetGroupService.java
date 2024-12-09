package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.PetGroup;
import org.vetclinic.domain.model.User;
import org.vetclinic.domain.repository.PetGroupRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PetGroupService
{
    private PetGroupRepository petGroupRepository;
    private UserService userService;

    public PetGroup getPetGroupByOwnerAndName(User owner, String name){
        PetGroup pet = null;
        try{
            Optional<PetGroup> optionalPet = petGroupRepository.findPetGroupByOwnerAndName(owner, name);
            if(optionalPet.isPresent()){
                pet = optionalPet.get();
            }
        }catch (Exception e){
            log.info("Pet not found for provided user and name.");
        }
        return pet;
    }
    public List<PetGroup> getPetGroupsForUser(String email){
        try{
            User owner = userService.getUserByEmail(email);
            if(owner != null) {
                return petGroupRepository.findAllByOwner(owner);
            }
        }catch (Exception e){
            log.info("Error fetching pets for user");
        }
        return List.of();
    }

    public boolean createNewPetGroup(String email, String petName, String breed, String typeStr, int size){
        try{
            //check if user exists by email
            User user = userService.getUserByEmail(email);
            if(user == null){
                throw new IllegalArgumentException("User not found for email: " + email);
            }
            if(!isNameValid(user, petName)){
                throw new IllegalArgumentException("User already owns a pet group with this name: " + petName);
            }
            //validate sex and type
            Pet.Type type = PetService.parseEnumValue(typeStr, PetGroup.Type.class, "Invalid type value: " + typeStr);

            //save pet
            petGroupRepository.save(new PetGroup(petName, type, breed, size, user));
            return true;
        }catch(Exception e){
            log.info("Error saving pet group: " + e.getMessage());
            return false;
        }
    }
    private boolean isNameValid(User owner, String name){
        return !petGroupRepository.existsByOwnerAndName(owner, name);
    }
}
