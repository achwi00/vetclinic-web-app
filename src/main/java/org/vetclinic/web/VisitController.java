package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.BookVisitRequest;
import org.vetclinic.api.dto.CustomVisitDto;
import org.vetclinic.api.dto.VisitDto;
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

    @GetMapping("/upcoming")
    public List<Visit> getAllUpcomingVisitsForUser(@RequestParam String email){
        return visitService.findAllVisitsInStatusForClient(email, Visit.VisitStatus.UPCOMING);
    }

    @GetMapping("/completed")
    public List<Visit> getAllCompletedVisitsForUser(@RequestParam String email){
        return visitService.findAllVisitsInStatusForClient(email, Visit.VisitStatus.COMPLETED);
    }
    @GetMapping("/cancel-visit")
    public String cancelVisit(@RequestParam Long visitId){
        log.info("visitId: " + visitId);
        String msg = "Successfully cancelled";
        if(!visitService.cancelVisit(visitId)){
            msg = "Error cancelling visit";
        }
        return msg;
    }

    @PostMapping("book-visit")
    public String newVisitForPet(@RequestBody BookVisitRequest request){
        String msg = "Successfully booked";
//        log.info("visitId and email" + request.getVisitId() + request.getEmail());
//        log.info("petName: " + request.getPetName());
        if(!visitService.bookVisit(request.getVisitId(), request.getEmail(), request.getPetName())){
            msg = "Error booking visit";
        }
        return msg;
    }

    @GetMapping("/vet/incoming")
    public List<Visit> getAllVisitsForVetForToday(@RequestParam String email){
        return visitService.findAllVisitsForTodayForVet(email);
    }

    @PostMapping("custom-visit")
    public ResponseEntity<String> newCustomVisit(@RequestBody CustomVisitDto request){
        log.info(request.toString());
        boolean success = visitService.createCustomVisit(
                request.getUserEmail(),
                request.getPetName(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime(),
                request.getVetEmail()

        );
        if (success) {
            return ResponseEntity.ok("Custom visit added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add custom visit");
        }
    }

    @GetMapping("/vet/all-incoming")
    public List<VisitDto> getAllIncomingVisitDtosForVet(@RequestParam String email) {
        return visitService.getAllVisitDtosForVet(email);
    }

    @GetMapping("/all-incoming")
    public List<VisitDto> getAllIncomingVisitDtos() {
        return visitService.getAllVisitDtos();
    }


}