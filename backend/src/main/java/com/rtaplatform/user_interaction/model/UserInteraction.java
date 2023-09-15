package com.rtaplatform.user_interaction.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
public class UserInteraction implements Serializable {
    private Long id;
    private Long created;
    private String uuid;
    private Long userId;
    private Long productId;
    private Integer timeSec;
}
