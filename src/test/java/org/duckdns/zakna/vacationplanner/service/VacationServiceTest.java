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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class VacationServiceTest {
    @Mock
    private VacationRepository vacationRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private VacationServiceImpl vacationService;


    @Test
    public void shouldCreateAVacation() {
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

        when(userService.getOrCreateUser(userName)).thenReturn(expectedUser);
        when(vacationRepository.save(any(Vacation.class))).thenReturn(expectedVacation);

        Vacation actualVacation = vacationService.createVacation(Vacation1Description, userName, startDate, endDate);
        assertEquals(expectedVacation, actualVacation);
        verify(vacationRepository).save(any(Vacation.class));
        verify(userService).getOrCreateUser(any(String.class));
    }

    @Test
    public void shouldReturnUserVacations() {
        String userName = "Olivier";

        String Vacation1Description = "Carnaval";
        LocalDate startDate1 = LocalDate.of(2025, 3, 10);
        LocalDate endDate1 = LocalDate.of(2025, 3, 15);

        String Vacation2Description = "Paques";
        LocalDate startDate2 = LocalDate.of(2025, 5, 8);
        LocalDate endDate2 = LocalDate.of(2025, 5, 15);

        User expectedUser = new User();
        expectedUser.setUsername(userName);

        Vacation expectedVacation1 = new Vacation();
        expectedVacation1.setDescription(Vacation1Description);
        expectedVacation1.setStartDate(startDate1);
        expectedVacation1.setEndDate(endDate1);
        expectedVacation1.setUser(expectedUser);

        Vacation expectedVacation2 = new Vacation();
        expectedVacation2.setDescription(Vacation2Description);
        expectedVacation2.setStartDate(startDate2);
        expectedVacation2.setEndDate(endDate2);
        expectedVacation2.setUser(expectedUser);

        List<Vacation> expectedVacations = List.of(expectedVacation1, expectedVacation2);

        when(userService.getOrCreateUser(userName)).thenReturn(expectedUser);
        when(vacationRepository.findVacationsByUser(expectedUser)).thenReturn(expectedVacations);

        List<Vacation> actualVacations = vacationService.getVacationsByUser(userName);
        assertEquals(expectedVacations, actualVacations);
        verify(vacationRepository).findVacationsByUser(expectedUser);
    }

    @Test
    public void shouldReturnVacationById() {
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
        expectedVacation.setId(1L);

        Long expectedId = expectedVacation.getId();

        when(vacationRepository.getReferenceById(any(Long.class))).thenReturn(expectedVacation);

        Long actualId = vacationService.getVacation(expectedId).getId();
        assertEquals(expectedId, actualId);

        verify(vacationRepository).getReferenceById(any(Long.class));
    }

    @Test
    public void shouldCancelVacationById() {
        Long expectedId = 1L;
        vacationService.cancelVacation(expectedId);
        verify(vacationRepository).deleteById(expectedId);
    }

    @Test
    public void shouldCreateAndCancelVacation() {
        String vacation1Description = "Carnaval";
        String userName = "Olivier";
        LocalDate startDate = LocalDate.of(2025, 3, 10);
        LocalDate endDate = LocalDate.of(2025, 3, 15);

        User user = new User();
        user.setUsername(userName);

        Vacation expectedVacation = new Vacation();
        expectedVacation.setDescription(vacation1Description);
        expectedVacation.setStartDate(startDate);
        expectedVacation.setEndDate(endDate);
        expectedVacation.setUser(user);
        expectedVacation.setId(1L);

        List<Vacation> emptyVacationList = new ArrayList<>();

        when(userService.getOrCreateUser(userName)).thenReturn(user);
        when(vacationRepository.save(any(Vacation.class))).thenReturn(expectedVacation);
        when(vacationRepository.findVacationsByUser(user)).thenReturn(emptyVacationList);

        Vacation actualVacation = vacationService.createVacation(vacation1Description, userName, startDate, endDate);

        assertThat(actualVacation)
                .usingRecursiveComparison()
                .isEqualTo(expectedVacation);

        vacationService.cancelVacation(actualVacation.getId());
        verify(vacationRepository).deleteById(actualVacation.getId());

        List<Vacation> vacationListAfterDeletion = vacationService.getVacationsByUser(userName);
        assertEquals(emptyVacationList, vacationListAfterDeletion);
        verify(userService, times(2)).getOrCreateUser(userName);
        verify(vacationRepository).save(any(Vacation.class));
        verify(vacationRepository).findVacationsByUser(user);
    }
}
