//package org.vetclinic.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.parameters.P;
//import org.vetclinic.model.BasePet;
//import org.vetclinic.model.Pet;
//import org.vetclinic.model.User;
//import org.vetclinic.repository.PetRepository;
//import org.vetclinic.repository.UserRepository;
//
//import java.time.Instant;
//
//@Configuration
//@AllArgsConstructor
//public class DataInitializer {
//
//    private final UserRepository userRepository;
//    private final PetRepository petRepository;
//
////    public DataInitializer(UserRepository userRepository) {
////        this.userRepository = userRepository;
////    }
//
//    @Bean
//    public ApplicationRunner initializer() {
//        return args -> {
//
//            User user = new User(null, "email@in.com", "password", User.Role.CLIENT);
//            userRepository.save(user);
//
////            Pet pet = new Pet("name", BasePet.Type.CAT, "persian", Instant.now(), user);
////            petRepository.save(pet);
//
//            System.out.println("User saved at startup: " + user);
//        };
//    }
//}
