package com.rtaplatform.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (userRepository.existingUserByFirstNameAndLastName(user.getFirstName(), user.getLastName())) {
            throw UserCreateException.exist();
        }

        return userRepository.save(user);
    }
}
