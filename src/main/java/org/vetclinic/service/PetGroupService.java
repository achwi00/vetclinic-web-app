package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.vetclinic.model.PetGroup;
import org.vetclinic.model.User;
import org.vetclinic.repository.PetGroupRepository;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PetGroupService
{
    private PetGroupRepository petGroupRepository;
    private UserService userService;

    public List<PetGroup> getPetsForUser(String email){
        try{
            User owner = userService.getUserByEmail(email);
            if(owner != null) {
                return petGroupRepository.findAllByOwner(owner);
            }
        }catch (Exception e){
            log.info("Error fetching pets for user");
        }
        return List.of();
    }
}
