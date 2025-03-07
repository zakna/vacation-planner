package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.duckdns.zakna.vacationplanner.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {


    private final VacationRepository vacationRepository;
    private final UserService userService;

    @Autowired
    public VacationServiceImpl(VacationRepository vacationRepository, UserService userService) {
        this.vacationRepository = vacationRepository;
        this.userService = userService;
    }

    @Override
    public Vacation createVacation(String description, String username, LocalDate startDate, LocalDate endDate) {
        User user = userService.getOrCreateUser(username);
        Vacation vacation = new Vacation();
        vacation.setUser(user);
        vacation.setDescription(description);
        vacation.setStartDate(startDate);
        vacation.setEndDate(endDate);
        return vacationRepository.save(vacation);
    }



    @Override
    public List<Vacation> getVacationsByUser(String username) {
        return vacationRepository.findVacationsByUser(userService.getOrCreateUser(username));
    }

    @Override
    public Vacation getVacation(Long id) {
        return vacationRepository.getReferenceById(id);
    }

    @Override
    public void cancelVacation(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public int getRemainingVacationDays(String username) {
        return userService.getOrCreateUser(username).getAvailableVacationDays();
    }
}
