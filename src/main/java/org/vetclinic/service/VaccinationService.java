package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.*;
import org.vetclinic.domain.repository.MedicationRepository;
import org.vetclinic.domain.repository.VaccinationRepository;
import org.vetclinic.domain.repository.VisitRepository;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VaccinationService
{
    private final VaccinationRepository vaccinationRepository;
    private final MedicationRepository medicationRepository;
    private final VisitRepository visitRepository;

    public List<Vaccination> getAllPreviousVaccinationsForBasePet(Long basePetId){
        return vaccinationRepository.getAllByBasePet_IdAndVaccinationStatus(basePetId, Vaccination.VaccinationStatus.DONE);
    }

    public boolean createNewVaccination(Long visitId, Long medicationId, int numOfPets){
        try{
            //get the visit
            Optional<Visit> optionalVisit = visitRepository.getVisitById(visitId);
            if(optionalVisit.isEmpty()){
                throw new IllegalArgumentException("Visit not found for id: " + visitId);
            }
            Visit visit = optionalVisit.get();
            //check if medication exists
            Medication medication = medicationRepository.getMedicationById(medicationId);
            if(medication == null){
                throw new IllegalArgumentException("Medication not found for id: " + medicationId);
            }
            vaccinationRepository.save(new Vaccination(null, medication, visit, visit.getDate(), true, visit.getBasePet(), Vaccination.VaccinationStatus.DONE, numOfPets));
            return true;
        }catch(Exception e){
            log.info("Error saving vaccination: " + e.getMessage());
            return false;
        }
    }
}
