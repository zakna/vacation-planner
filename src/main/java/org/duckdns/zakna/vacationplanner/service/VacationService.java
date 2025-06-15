package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.Vacation;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {
    Vacation createVacation(String description, String userName, LocalDate startDate, LocalDate endDate);

    List<Vacation> getVacationsByUser(String userName);

    Vacation getVacation(Long id);

    void cancelVacation(Long id);

    int getRemainingVacationDays(String userName);
}
