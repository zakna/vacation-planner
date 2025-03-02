package org.duckdns.zakna.vacationplanner.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setUsername("olivier");
        assertEquals("olivier", user.getUsername());
    }
}