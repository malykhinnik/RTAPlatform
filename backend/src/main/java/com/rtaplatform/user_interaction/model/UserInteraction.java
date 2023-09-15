package com.rtaplatform.user_interaction.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class UserInteraction implements Serializable {
    private Long id;
    private LocalDateTime created;
    private String uuid;
    private Long userId;
    private Long productId;
    private Integer timeSec;
}
