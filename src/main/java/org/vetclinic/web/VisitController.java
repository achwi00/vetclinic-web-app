package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vetclinic.model.Visit;
import org.vetclinic.service.VisitService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/visits")
public class VisitController
{
    private final VisitService visitService;

    @GetMapping("/in-between")
    public List<Visit> getAllPetGroupsForUser(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return visitService.findAllVisitsInBetween(startDate, endDate);
    }//check dates validation
}
