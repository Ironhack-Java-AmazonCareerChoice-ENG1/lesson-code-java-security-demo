package com.ironhack.demosecurityjwt.controllers.impl;

import com.ironhack.demosecurityjwt.controllers.GreetController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greet")
@Slf4j
public class GreetControllerImpl implements GreetController {

    @GetMapping
    @Override
    public String greet() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User {} has role {}", authentication.getName(), authentication.getAuthorities());
        return "hello " + authentication.getName();
    }

    @GetMapping("admin")
    @Override
    public String greetForAdmin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("User {} has role {}", authentication.getName(), authentication.getAuthorities());
        return "hello " + authentication.getName();
    }
}
