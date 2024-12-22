package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.TreatmentDto;
import org.vetclinic.api.dto.VaccinationDto;
import org.vetclinic.domain.model.Treatment;
import org.vetclinic.service.TreatmentService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class TreatmentController
{
    private final TreatmentService treatmentService;

    @GetMapping("/treatments")
    public List<Treatment> getAllTreatmentsForBasePet(@RequestParam Long basePetId){
        return treatmentService.getAllTreatmentsForBasePet(basePetId);
    }

    @PostMapping("/add-treatment")
    public ResponseEntity<String> addNewTreatment(@RequestBody TreatmentDto request){
        boolean success = treatmentService.createNewTreatment(
                request.getVisitId(),
                request.getMedicationName(),
                request.getNumOfPets(),
                request.getDescription(),
                request.getStartDate(),
                request.getEndDate()
        );
        if (success) {
            return ResponseEntity.ok("Treatment added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add treatment");
        }
    }
}
