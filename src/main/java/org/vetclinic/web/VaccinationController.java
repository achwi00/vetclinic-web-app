package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
