package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vetclinic.model.Pet;
import org.vetclinic.model.PetGroup;
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
        return petGroupService.getPetsForUser(email);
    }


}
