package com.rtaplatform.app_user;

import com.rtaplatform.app_user.exception.AppUserCreateException;
import com.rtaplatform.app_user.model.AppUser;
import com.rtaplatform.postgresql.app_user.AppUserRepository;
import com.rtaplatform.postgresql.app_user.entity.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser create(final AppUser appUser) {
        if (appUserRepository.existsAppUserByFirstNameAndLastName(appUser.getFirstName(), appUser.getLastName())) {
            throw AppUserCreateException.exist();
        }

        return appUserRepository.save(AppUserEntity.toEntity(appUser)).toModel();
    }

    public Optional<AppUser> getAppUser(final Long id) {
        return appUserRepository.findById(id).map(AppUserEntity::toModel);
    }

    public Page<AppUser> getAppUsers(final PageRequest pageRequest) {
        return appUserRepository.findAll(pageRequest).map(AppUserEntity::toModel);
    }

    public Optional<AppUser> updateAppUser(final Long id, final AppUser appUser) {
        return appUserRepository.findById(id)
                .map(AppUserEntity::toModel)
                .map(au -> au.update(appUser))
                .map(AppUserEntity::toEntity)
                .map(appUserRepository::save)
                .map(AppUserEntity::toModel);
    }

    public void deleteAppUser(final Long id) {
        appUserRepository.deleteById(id);
    }
}