package org.vetclinic.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.api.dto.ClientDto;
import org.vetclinic.service.UserService;

import java.util.List;


@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping(value = "/username")
//    @CrossOrigin
//    public String currentUserNameSimple() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null) log.info("authentication is null");
//        System.out.println(authentication.getName() + authentication.getCredentials() + authentication.getDetails());
//        return authentication.getName();
//    }

    @PostMapping("/role")
    public String getRole(@RequestBody String uMail){
        log.info("mail in getRole: " + uMail);
        return userService.getUserRole(uMail);
    }

    @GetMapping("/name")
    public String getName(@RequestParam String uMail){
        log.info("mail: " + uMail);
        return userService.getNameOfUser(uMail);
    }

    @GetMapping("/vet-emails")
    public List<String> allVetEmails(){
        return userService.getAllVetEmails();
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        //clear security context
        SecurityContextHolder.clearContext();
        //invalidate session
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewUser(@RequestBody ClientDto clientDto){
        boolean success = userService.addNewClientAccount(clientDto.getEmail(), clientDto.getName(), clientDto.getSurname(), clientDto.getPassword());
        if (success) {
            return ResponseEntity.ok("Client account added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add new client account.");
        }
    }

}
