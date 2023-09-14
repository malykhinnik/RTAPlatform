package com.rtaplatform.user_interaction.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("product")
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInteraction implements Serializable {
    @Id
    private Long id;
    private Long created;
    private String uuid;
    private Long userId;
    private Long productId;
    private Integer timeSec;
}
