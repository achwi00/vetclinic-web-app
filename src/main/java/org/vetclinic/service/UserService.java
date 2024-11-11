package org.vetclinic.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetclinic.model.User;
import org.vetclinic.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService
{
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
