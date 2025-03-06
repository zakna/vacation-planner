package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VacationServiceIntegrationTest {

    @Autowired
    VacationService vacationService;

    @Test
    public void shouldCreateAndDeleteAVacation() {
        String Vacation1Description = "Carnaval";
        String userName = "Olivier";
        LocalDate startDate = LocalDate.of(2025, 3, 10);
        LocalDate endDate = LocalDate.of(2025, 3, 15);

        User expectedUser = new User();
        expectedUser.setUsername(userName);

        Vacation expectedVacation = new Vacation();
        expectedVacation.setDescription(Vacation1Description);
        expectedVacation.setStartDate(startDate);
        expectedVacation.setEndDate(endDate);
        expectedVacation.setUser(expectedUser);
        // create the vacation
        Vacation actualVacation = vacationService.createVacation(Vacation1Description, userName, startDate, endDate);
        assertEquals(expectedVacation.getStartDate(), actualVacation.getStartDate());
        assertEquals(expectedVacation.getEndDate(), actualVacation.getEndDate());
        assertEquals(expectedVacation.getUser().getUsername(), actualVacation.getUser().getUsername());
        // delete the vacation
        vacationService.cancelVacation(actualVacation.getId());
        assertEquals(0, vacationService.getVacationsByUser(userName).size());
    }
}

