package com.ironhack.demosecurityjwt.controllers.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // Any setup code if required
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    public void greet_withAuthenticatedUser_returnsGreeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/greet"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello testUser"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"ADMIN"})
    public void greetAdmin_withAuthenticatedUser_returnsGreeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/greet/admin"))
            .andExpect(status().isOk())
            .andExpect(content().string("hello testUser"));
    }

    @Test
    public void greet_withoutAuthentication_returnsUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/greet"))
            .andExpect(status().isForbidden());
    }
}