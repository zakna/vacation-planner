package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;

public interface UserService {
    User getOrCreateUser(String username);
}
