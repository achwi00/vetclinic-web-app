package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Vaccination;
import org.vetclinic.domain.repository.VaccinationRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VaccinationService
{
    private final VaccinationRepository vaccinationRepository;

    public List<Vaccination> getAllPreviousVaccinationsForBasePet(Long basePetId){
        return vaccinationRepository.getAllByBasePet_IdAndVaccinationStatus(basePetId, Vaccination.VaccinationStatus.DONE);
    }
}
