package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.model.User;
import org.vetclinic.model.Visit;
import org.vetclinic.repository.VisitRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VisitService
{
    private final VisitRepository visitRepository;
    private UserService userService;

    public List<Visit> findAllVisitsInBetween(){
        return List.of();
    }

}
