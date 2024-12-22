package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Medication;
import org.vetclinic.domain.model.Treatment;
import org.vetclinic.domain.model.Vaccination;
import org.vetclinic.domain.model.Visit;
import org.vetclinic.domain.repository.MedicationRepository;
import org.vetclinic.domain.repository.TreatmentRepository;
import org.vetclinic.domain.repository.VisitRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TreatmentService
{
    private final TreatmentRepository treatmentRepository;
    private final VisitRepository visitRepository;
    private final MedicationRepository medicationRepository;

    public List<Treatment> getAllTreatmentsForBasePet(Long basePetId){
        return treatmentRepository.getAllByPet_Id(basePetId);
    }

    public boolean createNewTreatment(Long visitId, String medicationName, int numOfPets, String description, LocalDate startDate, LocalDate endDate){
        try{
            //get the visit
            Optional<Visit> optionalVisit = visitRepository.getVisitById(visitId);
            if(optionalVisit.isEmpty()){
                throw new IllegalArgumentException("Visit not found for id: " + visitId);
            }
            Visit visit = optionalVisit.get();
            //check if medication exists
            Medication medication = medicationRepository.getMedicationByName(medicationName);
            if(medication == null){
                throw new IllegalArgumentException("Medication not found for name: " + medicationName);
            }
            treatmentRepository.save(new Treatment(null, visit.getBasePet(), startDate, endDate, medication, description, numOfPets));
            return true;
        }catch(Exception e){
            log.info("Error saving treatment: " + e.getMessage());
            return false;
        }
    }

}
