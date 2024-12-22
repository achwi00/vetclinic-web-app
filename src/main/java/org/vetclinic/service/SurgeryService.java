package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Medication;
import org.vetclinic.domain.model.Surgery;
import org.vetclinic.domain.model.Vaccination;
import org.vetclinic.domain.model.Visit;
import org.vetclinic.domain.repository.MedicationRepository;
import org.vetclinic.domain.repository.SurgeryRepository;
import org.vetclinic.domain.repository.VisitRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SurgeryService
{
    private final SurgeryRepository surgeryRepository;
    private final VisitRepository visitRepository;

    public List<Surgery> getAllCompletedSurgeriesByBasePetId(Long basePetId){
        return surgeryRepository.getAllByBasePet_IdAndVisit_Status(basePetId, Visit.VisitStatus.COMPLETED);
    }

    public boolean createNewSurgery(Long visitId, int numOfPets, String description){
        try{
            //get the visit
            Optional<Visit> optionalVisit = visitRepository.getVisitById(visitId);
            if(optionalVisit.isEmpty()){
                throw new IllegalArgumentException("Visit not found for id: " + visitId);
            }
            Visit visit = optionalVisit.get();
            surgeryRepository.save(new Surgery(null, visit, description, visit.getBasePet(), visit.getDate(), numOfPets));
            return true;
        }catch(Exception e){
            log.info("Error saving surgery: " + e.getMessage());
            return false;
        }
    }
}
