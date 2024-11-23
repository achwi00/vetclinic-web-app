package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.model.Pet;
import org.vetclinic.model.User;
import org.vetclinic.repository.PetRepository;

import java.util.List;

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
}
