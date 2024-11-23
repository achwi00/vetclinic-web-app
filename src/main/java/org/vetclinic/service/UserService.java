package org.vetclinic.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetclinic.model.User;
import org.vetclinic.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService
{
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public String getUserRole(String email){
        User user;
        String role = null;
        try{
            Optional<User> optionalUser = userRepository.findUserByEmail(email);
            if(optionalUser.isPresent()) {
                user = optionalUser.get();
                role = user.getRole().toString();
            }
        }catch (Exception e){
            log.info("User not found for given email");
        }
       return role;
    }
}