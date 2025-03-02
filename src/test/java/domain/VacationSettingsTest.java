package domain;

import org.duckdns.zakna.vacationplanner.domain.VacationSettings;
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
        vacationSettings.setVacationDays(expectedDays);
        assertEquals(expectedDays, vacationSettings.getVacationDays());
    }

    @Test
    public void shouldHaveDefaultValuesWhenCreated() {
        assertNull(vacationSettings.getId());
        assertEquals(0, vacationSettings.getVacationDays());
    }


    @Test
    public void shouldUpdateVacationDaysWhenSetMultipleTimes() {
        vacationSettings.setVacationDays(10);
        assertEquals(10, vacationSettings.getVacationDays());
        vacationSettings.setVacationDays(20);
        assertEquals(20, vacationSettings.getVacationDays());
    }
}