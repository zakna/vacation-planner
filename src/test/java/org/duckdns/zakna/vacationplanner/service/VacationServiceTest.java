package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.duckdns.zakna.vacationplanner.repository.VacationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VacationServiceTest {
    @Mock
    private VacationRepository vacationRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private VacationServiceImpl vacationServiceImpl;

    @Test
    public void shouldCreateAVacation() {
        String description = "Carnaval";
        String userName = "Olivier";
        LocalDate startDate = LocalDate.of(2025, 3, 10);
        LocalDate endDate = LocalDate.of(2025, 3, 15);

        User expectedUser = new User();
        expectedUser.setUsername(userName);

        Vacation expectedVacation = new Vacation();
        expectedVacation.setDescription(description);
        expectedVacation.setStartDate(startDate);
        expectedVacation.setEndDate(endDate);
        expectedVacation.setUser(expectedUser);

        when(userService.getOrCreateUser(userName)).thenReturn(expectedUser);
        when(vacationRepository.save(any(Vacation.class))).thenReturn(expectedVacation);

        Vacation actualVacation = vacationServiceImpl.createVacation(description, userName, startDate, endDate);
        assertEquals(expectedVacation, actualVacation);
        verify(vacationRepository).save(any(Vacation.class));
    }
}
