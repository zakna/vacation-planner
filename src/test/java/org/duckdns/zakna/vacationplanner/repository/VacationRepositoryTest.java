package org.duckdns.zakna.vacationplanner.repository;

import jakarta.validation.ConstraintViolationException;
import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class VacationRepositoryTest {
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldNotBeAbleToRetrieveVacationWithNoStartOrEnDates() {
        Vacation vacation = new Vacation();
        vacation.setDescription("description");
        vacationRepository.save(vacation);
        assertThrows(
                ConstraintViolationException.class, () -> {
                    Vacation foundVacation = vacationRepository.findVacationByDescription("nullDates").orElse(null);
                });
    }

    @Test
    public void shouldBeAbleToRetrieveVacation() {
        Vacation vacation = new Vacation();
        vacation.setDescription("retrieve");
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 10, 15));
        vacationRepository.save(vacation);
        Vacation foundVacation = vacationRepository.findVacationByDescription("retrieve").orElse(null);
        assertNotNull(foundVacation);
        assertEquals("retrieve", foundVacation.getDescription());
    }

    @Test
    public void shouldBeAbleToFindVacationByUser() {

        User user = new User();
        user.setUserName("user");
        userRepository.save(user);

        Vacation vacation = new Vacation();
        vacation.setDescription("description");
        vacation.setStartDate(LocalDate.of(2025, 10, 1));
        vacation.setEndDate(LocalDate.of(2025, 10, 15));
        vacation.setUser(user);

        vacationRepository.save(vacation);

        List<Vacation> foundVacations = vacationRepository.findVacationsByUser(user);

        assertNotNull(foundVacations);
        assertEquals(1, foundVacations.size());
        assertEquals("description", foundVacations.getFirst().getDescription());
        assertEquals("user", foundVacations.getFirst().getUser().getUserName());
    }
}
