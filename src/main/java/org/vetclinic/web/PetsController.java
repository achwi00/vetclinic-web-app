package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.BookVisitRequest;
import org.vetclinic.api.dto.PetRequest;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.PetGroup;
import org.vetclinic.service.PetGroupService;
import org.vetclinic.service.PetService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class PetsController
{
    private final PetService petService;
    private final PetGroupService petGroupService;

    @GetMapping("/pets")
    public List<Pet> getAllPetsForUser(@RequestParam String email){
        return petService.getPetsForUser(email);
    }

    @GetMapping("/pet-group")
    public List<PetGroup> getAllPetGroupsForUser(@RequestParam String email){
        return petGroupService.getPetGroupsForUser(email);
    }

    @PostMapping("/new-pet")
    public ResponseEntity<String> newVisitForPet(@RequestBody PetRequest request){
        boolean success = petService.createNewPet(
                request.getEmail(),
                request.getPetName(),
                request.getBreed(),
                request.getType(),
                request.getBirthDate(),
                request.getSex()
        );
        if (success) {
            return ResponseEntity.ok("Pet created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create pet");
        }
    }


}
