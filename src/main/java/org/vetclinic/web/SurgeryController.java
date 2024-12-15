package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
