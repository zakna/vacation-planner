package org.duckdns.zakna.vacationplanner.controller;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        when(userService.getUser(userName)).thenReturn(Optional.of(expectedUser));
        mockMvc.perform(get("/api/users/{username}", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userName));
    }

    @Test
    void shouldNotRetrieveNonExistingUser() throws Exception {
        String userName = "NonExistentUser";
        when(userService.getUser(userName)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/users/{username}", userName))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateANewUser() throws Exception {
        String userName = "newUser";
        User user = new User();
        user.setUsername(userName);
        when(userService.createUser(userName)).thenReturn(user);
        mockMvc.perform(post("/api/users/").param("username", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userName));
    }

    @Test
    void shouldReturnBadRequestWhenCreatingUserWithEmptyUsername() throws Exception {
        mockMvc.perform(post("/api/users/").param("username", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingUserWithNullUsername() throws Exception {
        mockMvc.perform(post("/api/users/").param("username", (String) null))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldHandleExceptionWhenUserServiceThrowsException() throws Exception {
        String userName = "problemUser";
        when(userService.getUser(userName)).thenThrow(new RuntimeException("Service error"));
        mockMvc.perform(get("/api/users/{username}", userName))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldHandleExceptionWhenUserCreationFails() throws Exception {
        String userName = "problemUser";
        when(userService.createUser(userName)).thenThrow(new RuntimeException("Creation error"));
        mockMvc.perform(post("/api/users/").param("username", userName))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingUserWithNoUsername() throws Exception {
        mockMvc.perform(post("/api/users/"))
                .andExpect(status().isBadRequest());
    }

}