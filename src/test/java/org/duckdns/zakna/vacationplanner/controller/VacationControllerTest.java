package org.duckdns.zakna.vacationplanner.controller;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.duckdns.zakna.vacationplanner.service.VacationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VacationController.class)
public class VacationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VacationService vacationService;

    @Test
    void shouldCreateVacation() throws Exception {
        LocalDate vacationStartDate = LocalDate.now();

        LocalDate vacationEndDate = LocalDate.now().plusDays(1);

        String vacationDescription = "vacation-description";
        String vacationUsername = "vacation-userName";
        int vacationDays = 20;
        User vacationUser = new User();
        vacationUser.setUserName("vacation-userName");
        vacationUser.setAvailableVacationDays(vacationDays);
        Vacation vacation = new Vacation();
        vacation.setDescription(vacationDescription);
        vacation.setUser(vacationUser);
        vacation.setStartDate(LocalDate.now());
        vacation.setEndDate(LocalDate.now().plusDays(1));

        when(vacationService.createVacation(vacationDescription, vacationUsername, vacationStartDate, vacationEndDate)).thenReturn(vacation);
                        mockMvc.perform(post("/api/vacations/")
                                        .param("description", vacationDescription)
                                        .param("startDate", String.valueOf(vacationStartDate))
                                        .param("endDate", String.valueOf(vacationEndDate))
                                        .param("userName", vacationUsername))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.description").value(vacationDescription))
                                .andExpect(jsonPath("$.startDate").value(vacationStartDate.toString()))
                                .andExpect(jsonPath("$.endDate").value(vacationEndDate.toString()))
                                .andExpect(jsonPath("$.user.userName").value(vacationUsername));
    }
}