package com.rtaplatform.app_user;

import com.rtaplatform.app_user.exception.AppUserCreateException;
import com.rtaplatform.app_user.model.AppUser;
import com.rtaplatform.postgresql.app_user.AppUserEntityRepository;
import com.rtaplatform.postgresql.app_user.entity.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserEntityRepository appUserEntityRepository;

    @Autowired
    public AppUserService(final AppUserEntityRepository appUserEntityRepository) {
        this.appUserEntityRepository = appUserEntityRepository;
    }

    public AppUser create(final AppUser appUser) {
        if (appUserEntityRepository.existsAppUserByFirstNameAndLastName(appUser.getFirstName(), appUser.getLastName())) {
            throw AppUserCreateException.exist();
        }

        return appUserEntityRepository.save(AppUserEntity.toEntity(appUser)).toModel();
    }

    public Optional<AppUser> getAppUser(final Long id) {
        return appUserEntityRepository.findById(id).map(AppUserEntity::toModel);
    }

    public Page<AppUser> getAppUsers(final PageRequest pageRequest) {
        return appUserEntityRepository.findAll(pageRequest).map(AppUserEntity::toModel);
    }

    public Optional<AppUser> updateAppUser(final Long id, final AppUser appUser) {
        return appUserEntityRepository.findById(id)
                .map(AppUserEntity::toModel)
                .map(au -> au.update(appUser))
                .map(AppUserEntity::toEntity)
                .map(appUserEntityRepository::save)
                .map(AppUserEntity::toModel);
    }

    public void deleteAppUser(final Long id) {
        appUserEntityRepository.deleteById(id);
    }
}