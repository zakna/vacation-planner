package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.Vacation;

import java.util.List;

public interface VacationService {
    Vacation createVacation(String description, String username);
    List<Vacation> getVacationByDescription(String description);
    List<Vacation> getVacationByUser(String username);
    void cancelVacation(String description, String username);
    Vacation UpdateVacation(String description, String username);
}
