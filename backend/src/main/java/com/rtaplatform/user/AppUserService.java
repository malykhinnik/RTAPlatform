package com.rtaplatform.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser create(AppUser appUser) {
        if (appUserRepository.existsAppUserByFirstNameAndLastName(appUser.getFirstName(), appUser.getLastName())) {
            throw AppUserCreateException.exist();
        }

        return appUserRepository.save(appUser);
    }
}
