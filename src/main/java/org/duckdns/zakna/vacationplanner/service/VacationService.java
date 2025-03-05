package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.Vacation;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {
    Vacation createVacation(String description, String username, LocalDate startDate, LocalDate endDate);

    List<Vacation> getVacationsByUser(String username);

    Vacation getVacation(String userName, String description);

    void cancelVacation(String description, String username);

    Vacation UpdateVacation(String description, String username, LocalDate startDate, LocalDate endDate);

    int getRemainingVacationDays(String username);
}
