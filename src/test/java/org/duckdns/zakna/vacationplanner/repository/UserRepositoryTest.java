package org.duckdns.zakna.vacationplanner.repository;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername(){
        User user = new User();
        user.setUsername("test");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("test").orElse(null);

    assertNotNull(foundUser);
    assertEquals("test",foundUser.getUsername());
    }
}
