package com.ironhack.demosecurityjwt.dev;

import com.ironhack.demosecurityjwt.models.Role;
import com.ironhack.demosecurityjwt.models.User;
import com.ironhack.demosecurityjwt.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {


    private final UserService userService;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userService.saveRole(new Role("ROLE_USER"));
        userService.saveRole(new Role("ROLE_ADMIN"));

        userService.saveUser(new User("primo tizio", "user", "1234", new ArrayList<>()));
        userService.saveUser(new User("secondo caio", "user2", "1234", new ArrayList<>()));
        userService.saveUser(new User("terzo sempronio", "admin", "1234", new ArrayList<>()));

        userService.addRoleToUser("user", "ROLE_USER");
        userService.addRoleToUser("user2", "ROLE_USER");
        userService.addRoleToUser("admin", "ROLE_ADMIN");
    }
}
