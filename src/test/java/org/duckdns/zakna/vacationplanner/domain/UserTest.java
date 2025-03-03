package org.duckdns.zakna.vacationplanner.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setUsername("olivier");
        assertEquals("olivier", user.getUsername());
    }

    @Test
    public void shouldRetrieveVacations() {
        User user = new User();
        user.setUsername("olivier");

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
}