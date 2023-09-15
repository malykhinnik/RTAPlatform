package com.rtaplatform.postgresql.app_user.entity;


import com.rtaplatform.app_user.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.rtaplatform.utils.DateTimeUtils.getNowTruncatedToMillis;


@Table(name = "app_user")
@Entity
@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    @With
    private String firstName;
    @With
    private String lastName;

    public static AppUserEntity toEntity(AppUser model) {
        return AppUserEntity.builder()
                .id(model.getId())
                .created(model.getCreated())
                .updated(model.getUpdated())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .build();
    }

    public AppUser toModel() {
        return AppUser.builder()
                .id(id)
                .created(created)
                .updated(updated)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    @PrePersist
    private void setCreatedAndUpdatedNow() {
        created = getNowTruncatedToMillis();
        updated = getNowTruncatedToMillis();
    }

    @PreUpdate
    private void setUpdatedNow() {
        updated = getNowTruncatedToMillis();
    }

}
