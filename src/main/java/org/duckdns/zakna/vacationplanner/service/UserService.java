package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;

import java.util.Optional;

public interface UserService {
    User createUser(String username);

    Optional<User> getUser(String userName);
}
