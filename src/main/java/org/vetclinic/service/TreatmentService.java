package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Treatment;
import org.vetclinic.domain.repository.TreatmentRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TreatmentService
{
    private final TreatmentRepository treatmentRepository;

    public List<Treatment> getAllTreatmentsForBasePet(Long basePetId){
        return treatmentRepository.getAllByPet_Id(basePetId);
    }

}
