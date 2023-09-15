package com.rtaplatform.app_user.model;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class AppUser {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    @With
    private String firstName;
    @With
    private String lastName;

    public AppUser update(final AppUser appUser) {
        return this
                .withFirstName(appUser.getFirstName())
                .withLastName(appUser.getLastName());
    }
}
