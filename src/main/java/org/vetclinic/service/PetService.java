package org.vetclinic.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.BasePet;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.User;
import org.vetclinic.domain.repository.PetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PetService
{
    private PetRepository petRepository;
    private PetGroupService petGroupService;
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

    public BasePet getBasePetByOwnerAndName(String email, String name){
        try{
            User owner = userService.getUserByEmail(email);
            if(owner == null){
                throw new EntityNotFoundException("User " + email + " not found");
            }
            BasePet basePet;
            basePet = getPetByOwnerAndName(owner, name);
            if(basePet == null){
                basePet = petGroupService.getPetGroupByOwnerAndName(owner, name);
            }
            return basePet;
        }catch(Exception e){
            log.info("Error getting BasePet for user and name");
            return null;
        }
    }

    public boolean createNewPet(String email, String petName, String breed, String typeStr, LocalDate birthDate, String sexStr){
        try{
            //check if user exists by email
            User user = userService.getUserByEmail(email);
            if(user == null){
                throw new IllegalArgumentException("User not found for email: " + email);
            }
            if(!isNameValid(user, petName)){
                throw new IllegalArgumentException("User already owns a pet with this name: " + petName);
            }
            //validate sex and type
            Pet.Sex sex = parseEnumValue(sexStr, Pet.Sex.class, "Invalid sex value: " + sexStr);
            Pet.Type type = parseEnumValue(typeStr, Pet.Type.class, "Invalid type value: " + typeStr);

            //save pet
            petRepository.save(new Pet(petName, type, breed, birthDate, user, sex));
            return true;
        }catch(Exception e){
            log.info("Error saving pet: " + e.getMessage());
            return false;
        }
    }

    protected static <E extends Enum<E>> E parseEnumValue(String value, Class<E> enumType, String errorMessage) {
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean isNameValid(User owner, String name){
        return !petRepository.existsByOwnerAndName(owner, name);
    }
}
