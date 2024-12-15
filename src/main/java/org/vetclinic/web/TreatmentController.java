package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
