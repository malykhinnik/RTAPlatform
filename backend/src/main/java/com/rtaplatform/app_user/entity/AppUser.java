package com.rtaplatform.app_user.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    @With
    private String firstName;
    @With
    private String lastName;

    @PrePersist
    private void setCreatedAndUpdatedNow() {
        created = getDateTimeNowTruncatedToMillis();
        updated = getDateTimeNowTruncatedToMillis();
    }

    @PreUpdate
    private void setUpdatedNow() {
        updated = getDateTimeNowTruncatedToMillis();
    }

    public AppUser update(final AppUser appUser) {

        return this
                .withFirstName(appUser.getFirstName())
                .withLastName(appUser.getLastName());
    }

    private LocalDateTime getDateTimeNowTruncatedToMillis() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
