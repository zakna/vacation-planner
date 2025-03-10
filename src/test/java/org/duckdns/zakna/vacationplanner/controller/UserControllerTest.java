package org.duckdns.zakna.vacationplanner.controller;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void shouldRetrieveExistingUser() throws Exception {
        String userName = "Olivier";
        User expectedUser = new User();
        expectedUser.setUsername(userName);
        when(userService.getOrCreateUser(userName)).thenReturn(expectedUser);

        mockMvc.perform(get("/api/users/{username}", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userName));
    }
}