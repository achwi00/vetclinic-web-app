package org.vetclinic.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.vetclinic.service.UserService;


@RestController
@Slf4j
@AllArgsConstructor
public class UserController {//added - whole controller

    private final UserService userService;

    @GetMapping(value = "/username")
    @CrossOrigin
    public String currentUserNameSimple() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) log.info("authentication is null");
        System.out.println(authentication.getName() + authentication.getCredentials() + authentication.getDetails());
        return authentication.getName();
    }

    @PostMapping("/role")
    public String getRole(@RequestBody String uMail){
        log.info("mail in getRole: " + uMail);
        return userService.getUserRole(uMail);
    }


}
