package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.vetclinic.api.dto.VaccinationDto;
import org.vetclinic.domain.model.Vaccination;
import org.vetclinic.service.VaccinationService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class VaccinationController
{
    private final VaccinationService vaccinationService;

    @GetMapping("/vaccination")
    public List<Vaccination> getAllCompletedVaccinationsByBasePetId(@RequestParam Long basePetId){
        return vaccinationService.getAllPreviousVaccinationsForBasePet(basePetId);
    }

    @PostMapping("/add-vaccination")
    public ResponseEntity<String> addNewVaccination(@RequestBody VaccinationDto request){
        boolean success = vaccinationService.createNewVaccination(
                request.getVisitId(),
                request.getMedicationName(),
                request.getNumOfPets()
        );
        if (success) {
            return ResponseEntity.ok("Vaccination added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add vaccination");
        }
    }
}
