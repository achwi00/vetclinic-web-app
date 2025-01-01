package org.vetclinic.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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

}
