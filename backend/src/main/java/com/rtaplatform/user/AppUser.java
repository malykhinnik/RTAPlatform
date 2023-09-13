package com.rtaplatform.user;


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
    private Long Id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String firstName;
    private String lastName;

    @PrePersist
    public void setCreatedAndUpdatedNow() {
        created = getDateTimeNowTruncatedToMillis();
        updated = getDateTimeNowTruncatedToMillis();
    }

    @PreUpdate
    public void setUpdatedNow() {
        updated = getDateTimeNowTruncatedToMillis();
    }

    public void updateFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    private LocalDateTime getDateTimeNowTruncatedToMillis() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
