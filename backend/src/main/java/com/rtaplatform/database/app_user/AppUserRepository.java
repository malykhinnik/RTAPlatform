package com.rtaplatform.database.app_user;

import com.rtaplatform.app_user.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long>, PagingAndSortingRepository<AppUser, Long> {
    boolean existsAppUserByFirstNameAndLastName(final String firstName, final String lastName);
}
