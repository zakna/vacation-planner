package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldGetExistingUser() {
        User user = new User();
        user.setUsername("existing");

        when(userRepository.findByUsername("existing")).thenReturn(Optional.of(user));

        User result = userService.getOrCreateUser("existing");
        assertEquals("existing", result.getUsername());
    }

    @Test
    public void shouldCreateNewUser() {
        User newUser = new User();
        newUser.setUsername("new");

        when(userRepository.findByUsername("new")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User result = userService.getOrCreateUser("new");
        assertEquals("new", result.getUsername());
    }

}
