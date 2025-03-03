package org.duckdns.zakna.vacationplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@Entity
public class Vacation {

    @Id
    private Long id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    private User user;

    public int getVacationDays() {
        Period period = Period.between(startDate, endDate);
        return period.getDays() + 1;
    }
}
