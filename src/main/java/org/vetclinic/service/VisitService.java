package org.vetclinic.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Pet;
import org.vetclinic.domain.model.User;
import org.vetclinic.domain.model.Visit;
import org.vetclinic.domain.repository.VisitRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VisitService
{
    private final VisitRepository visitRepository;
    private final UserService userService;
    private final PetService petService;

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
            LocalTime endTime = LocalTime.now().plusHours(2);
            log.info("endTime: " + endTime);
            return visitRepository.findAllByVetAndDateAndEndTimeBefore(vet, LocalDate.now(), endTime);
        }catch (Exception e){
            log.info("Exception during findAllVisitsForTodayForVet: " + e.getMessage());
            return List.of();
        }
    }

}
