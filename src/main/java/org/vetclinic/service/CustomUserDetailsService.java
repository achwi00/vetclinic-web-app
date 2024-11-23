package org.vetclinic.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.vetclinic.model.User;
import org.vetclinic.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println("in loadbyusername, email: "+ email);
        User user;
        try {
            user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new Exception("User not found with email: " + email));
            System.out.println("user role: " + user.getRole());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities() // Convert roles to authorities
        );
    }
}