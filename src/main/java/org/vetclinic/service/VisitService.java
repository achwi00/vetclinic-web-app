package org.vetclinic.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.*;
import org.vetclinic.domain.repository.VisitRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class VisitService
{
    private final VisitRepository visitRepository;
    private final UserService userService;
    private final PetService petService;
    private final PetGroupService petGroupService;

    public List<Visit> findAllVisitsInBetween(LocalDate startDate, LocalDate endDate){
        return visitRepository.findAllByDateBetweenAndStatus(startDate, endDate, Visit.VisitStatus.FREE);
    }

    public List<Visit> findAllVisitsInStatusForClient(String email, Visit.VisitStatus status){
        try{
            User client = userService.getUserByEmail(email);
            if(client == null){
                throw new EntityNotFoundException("Client not found for email");
            }
            return visitRepository.findAllByClientAndStatus(client, status);
        }catch (Exception e){
            log.info("Exception during findAllVisitsInStatusForClient: " + e.getMessage());
            return List.of();
        }
    }

    public boolean bookVisit(Long visitId, String email, String petName){
        try{
            //look for owner and pet based on arguments
            User owner = userService.getUserByEmail(email);
            Pet pet = petService.getPetByOwnerAndName(owner, petName);
            if(pet == null || owner == null){
                throw new IllegalArgumentException("Pet or owner id not provided");
            }
            //look for visit and ensure bookable status
            Visit visit = visitRepository.findById(visitId)
                    .orElseThrow(() -> new IllegalArgumentException("Visit not found with ID: " + visitId));

            if (!visit.getStatus().equals(Visit.VisitStatus.FREE)) {
                throw new IllegalStateException("Visit is not in a bookable state.");
            }
            //change the visit details
            visit.setClient(owner);
            visit.setBasePet(pet);
            visit.setStatus(Visit.VisitStatus.UPCOMING);
            //save the updated visit and return true to provide info about successful booking
            visitRepository.save(visit);
            return true;
        }catch (Exception exception){
            log.info("Error while visit booking");
            return false;
        }
    }

    public boolean cancelVisit(Long visitId){
        try{
            Visit visit = visitRepository.findById(visitId)
                    .orElseThrow(() -> new IllegalArgumentException("Visit not found with ID: " + visitId));
            visit.setClient(null);
            visit.setBasePet(null);
            visit.setStatus(Visit.VisitStatus.FREE);
            visitRepository.save(visit);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Visit> findAllVisitsForTodayForVet(String email){
        try{
            User vet = userService.getUserByEmail(email);
            if(vet == null){
                throw new EntityNotFoundException("Vet not found for email");
            }
            log.info("email: " + vet.getEmail());
            log.info("date: " + LocalDate.now());
            LocalTime endTime = LocalTime.now().minusHours(2);
            log.info("endTime: " + endTime);
            return visitRepository.findAllByVetAndDateAndEndTimeAfter(vet, LocalDate.now(), endTime);
        }catch (Exception e){
            log.info("Exception during findAllVisitsForTodayForVet: " + e.getMessage());
            return List.of();
        }
    }

    public boolean createCustomVisit(String userEmail, String petName, LocalDate date, LocalTime startTime, LocalTime endTime, String vetEmail){
        try{
            validateUserRole(userEmail, "CLIENT", userEmail + " is not a client");
            validateUserRole(vetEmail, "VET", vetEmail + " is not a vet");
            //get the users
            User client = userService.getUserByEmail(userEmail);
            User vet = userService.getUserByEmail(vetEmail);

            BasePet basePet = findPetOrGroup(client, petName);
            if (basePet == null) {
                throw new EntityNotFoundException("BasePet not found for client");
            }

            visitRepository.save(new Visit(null, date, startTime, endTime, client, vet, basePet, Visit.VisitStatus.UPCOMING));
            return true;
        }catch(Exception e){
            log.info("Error saving vaccination: " + e.getMessage());
            return false;
        }
    }
    private void validateUserRole(String email, String expectedRole, String errorMessage) {
        String role = userService.getUserRole(email);
        if (!Objects.equals(role, expectedRole)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    private BasePet findPetOrGroup(User client, String petName) {
        BasePet pet = petService.getPetByOwnerAndName(client, petName);
        if (pet != null) {
            return pet;
        }
        return petGroupService.getPetGroupByOwnerAndName(client, petName);
    }
}
