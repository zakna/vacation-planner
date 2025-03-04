package org.duckdns.zakna.vacationplanner.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VacationTest {

    @Test
    public void shouldBeAbleToCreateVacation() {
        Vacation vacation = new Vacation();
        assertNotNull(vacation);
    }

    @Test
    public void shouldbeAbleToCreateVacationWithStartAndEndDate() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 10, 15));
        assertEquals(LocalDate.of(2025, 10, 1), vacation.getStartDate());
        assertEquals(LocalDate.of(2025, 10, 15), vacation.getEndDate());
    }

    @Test
    public void shouldbeAbleToCreateVacationWithUser() {
        Vacation vacation = new Vacation();
        User user = new User();
        vacation.setUser(user);
        assertEquals(user, vacation.getUser());
    }

    @Test
    public void shouldBeAbleToComputeVacationDays() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 10, 15));
        assertEquals(15, vacation.getVacationDays());
    }

    @Test
    public void shouldBeAbleToComputeVacationDaysAcrossMonths() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 11, 1));
        assertEquals(32, vacation.getVacationDays());
    }

    @Test
    public void shouldBeAbleToComputeVacationDaysForOneDayVacation() {
        Vacation vacation = new Vacation();
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 10, 1));
        assertEquals(1, vacation.getVacationDays());
    }

    @Test
    public void shouldBeAbleToCreateVacationWithDescription() {
        Vacation vacation = new Vacation();
        vacation.setDescription("Test");
        assertEquals("Test", vacation.getDescription());
    }
}
