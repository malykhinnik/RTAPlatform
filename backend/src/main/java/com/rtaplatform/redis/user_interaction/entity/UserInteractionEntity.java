package com.rtaplatform.redis.user_interaction.entity;

import com.rtaplatform.user_interaction.model.UserInteraction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("user_interaction")
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInteractionEntity implements Serializable {
    @Id
    private Long id;
    private Long created;
    private String uuid;
    private Long userId;
    private Long productId;
    private Integer timeSec;

    public static UserInteractionEntity toEntity(UserInteraction model) {
        return UserInteractionEntity.builder()
                .id(model.getId())
                .created(model.getCreated())
                .uuid(model.getUuid())
                .userId(model.getUserId())
                .productId(model.getProductId())
                .timeSec(model.getTimeSec())
                .build();
    }
}
