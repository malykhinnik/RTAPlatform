package com.rtaplatform.postgresql.app_user;

import com.rtaplatform.postgresql.app_user.entity.AppUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppUserEntityRepository extends CrudRepository<AppUserEntity, Long>, PagingAndSortingRepository<AppUserEntity, Long> {
    boolean existsAppUserByFirstNameAndLastName(final String firstName, final String lastName);
}
