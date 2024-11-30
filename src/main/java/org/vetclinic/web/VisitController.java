package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.BookVisitRequest;
import org.vetclinic.domain.model.Visit;
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

    @PostMapping("book-visit")
    public String newVisitForPet(@RequestBody BookVisitRequest request){
        log.info("visitId and email" + request.getVisitId() + request.getEmail());
        log.info("petName: " + request.getPetName());
        visitService.bookVisit(request.getVisitId(), request.getEmail(), request.getPetName());
        return "Successfully booked";
    }
}
