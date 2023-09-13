package com.rtaplatform.user;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    boolean existsAppUserByFirstNameAndLastName(String firstName, String lastName);
}
