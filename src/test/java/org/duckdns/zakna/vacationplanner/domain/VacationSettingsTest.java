package org.duckdns.zakna.vacationplanner.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VacationSettingsTest {

    private VacationSettings vacationSettings;

    @BeforeEach
    public void setUp() {
        vacationSettings = new VacationSettings();
    }

    @Test
    public void shouldCreateNewVacationSettings() {
        assertNotNull(vacationSettings);
    }

    @Test
    public void shouldSetAndGetId() {
        Long expectedId = 1L;
        vacationSettings.setId(expectedId);
        assertEquals(expectedId, vacationSettings.getId());
    }

    @Test
    public void shouldSetAndGetVacationDays() {
        int expectedDays = 35;
        vacationSettings.setAvailableVacationDays(expectedDays);
        assertEquals(expectedDays, vacationSettings.getAvailableVacationDays());
    }

    @Test
    public void shouldHaveDefaultValuesWhenCreated() {
        assertNull(vacationSettings.getId());
        assertEquals(0, vacationSettings.getAvailableVacationDays());
    }


    @Test
    public void shouldUpdateVacationDaysWhenSetMultipleTimes() {
        vacationSettings.setAvailableVacationDays(10);
        assertEquals(10, vacationSettings.getAvailableVacationDays());
        vacationSettings.setAvailableVacationDays(20);
        assertEquals(20, vacationSettings.getAvailableVacationDays());
    }
}