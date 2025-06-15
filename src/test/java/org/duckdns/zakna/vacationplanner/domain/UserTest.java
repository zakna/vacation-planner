package org.duckdns.zakna.vacationplanner.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    public User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserName("olivier");
    }


    @Test
    public void shouldCreateUser() {
        assertEquals("olivier", user.getUserName());
    }

    @Test
    public void shouldRetrieveVacations() {

        List<Vacation> vacations = new ArrayList<>();
        Vacation vacation1 = new Vacation();
        Vacation vacation2 = new Vacation();
        vacations.add(vacation1);
        vacations.add(vacation2);

        user.setVacations(vacations);
        List<Vacation> retrievedVacations = user.getVacations();

        assertEquals(2, retrievedVacations.size());
        assertTrue(retrievedVacations.contains(vacation1));
        assertTrue(retrievedVacations.contains(vacation2));
    }


    @Test
    public void shouldSetAndGetVacationDays() {
        int expectedDays = 35;
        user.setAvailableVacationDays(expectedDays);
        assertEquals(expectedDays, user.getAvailableVacationDays());
    }

    @Test
    public void shouldHaveDefaultValuesWhenCreated() {
        assertEquals(0, user.getAvailableVacationDays());
    }


    @Test
    public void shouldUpdateVacationDaysWhenSetMultipleTimes() {
        user.setAvailableVacationDays(10);
        assertEquals(10, user.getAvailableVacationDays());
        user.setAvailableVacationDays(20);
        assertEquals(20, user.getAvailableVacationDays());
    }
}