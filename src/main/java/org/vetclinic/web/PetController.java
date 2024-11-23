package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vetclinic.model.Pet;
import org.vetclinic.service.PetService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class PetController
{
    private final PetService petService;

    @GetMapping("/pets")
    public List<Pet> getAllPetsForUser(@RequestParam String email){
        return petService.getPetsForUser(email);
    }


}
