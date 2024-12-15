package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.domain.model.Surgery;
import org.vetclinic.domain.model.Visit;
import org.vetclinic.domain.repository.SurgeryRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SurgeryService
{
    private final SurgeryRepository surgeryRepository;

    public List<Surgery> getAllCompletedSurgeriesByBasePetId(Long basePetId){
        return surgeryRepository.getAllByBasePet_IdAndVisit_Status(basePetId, Visit.VisitStatus.COMPLETED);
    }
}
