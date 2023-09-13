package com.rtaplatform.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existingUserByFirstNameAndLastName(String firstName, String lastName);
}
