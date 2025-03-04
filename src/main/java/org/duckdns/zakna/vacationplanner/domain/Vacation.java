package org.duckdns.zakna.vacationplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
public class Vacation {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @ManyToOne
    private User user;

    public int getVacationDays() {
        return ((int) ChronoUnit.DAYS.between(startDate, endDate)) + 1;
    }
}
