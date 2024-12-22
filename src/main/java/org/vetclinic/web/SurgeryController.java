package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.TreatmentDto;
import org.vetclinic.domain.model.Surgery;
import org.vetclinic.service.SurgeryService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class SurgeryController
{
    private final SurgeryService surgeryService;

    @GetMapping("/completed-surgeries")
    public List<Surgery> getAllCompletedSurgeriesForBasePetId(@RequestParam Long basePetId){
        return surgeryService.getAllCompletedSurgeriesByBasePetId(basePetId);
    }

    @PostMapping("/add-surgery")
    public ResponseEntity<String> addNewSurgery(@RequestBody TreatmentDto request){
        boolean success = surgeryService.createNewSurgery(
                request.getVisitId(),
                request.getNumOfPets(),
                request.getDescription()
        );
        if (success) {
            return ResponseEntity.ok("Surgery added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add surgery");
        }
    }
}
