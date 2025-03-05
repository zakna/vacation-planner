package org.duckdns.zakna.vacationplanner.repository;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    Optional<Vacation> findVacationByDescription(String description);
    Optional <List<Vacation>> findVacationsByUser(User user);
}
