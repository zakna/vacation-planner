package org.duckdns.zakna.vacationplanner.controller;

import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.duckdns.zakna.vacationplanner.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class VacationController {
    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping("/vacations/")
    public ResponseEntity<Vacation> createVacation(@RequestParam String description, @RequestParam String username, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        if (description == null || description.isEmpty() ||
            username == null || username.isEmpty() ||
            startDate == null || endDate == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(vacationService.createVacation(description,username,startDate,endDate));
    }
}

