package org.duckdns.zakna.vacationplanner.service;

import org.duckdns.zakna.vacationplanner.domain.User;
import org.duckdns.zakna.vacationplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(String userName) {
        return userRepository.findByUsername(userName)
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername(userName);
                    return userRepository.save(user);
                });
    }

    @Override
    public Optional<User> getUser(String userName) {
        return userRepository.findByUsername(userName);

    }
}
