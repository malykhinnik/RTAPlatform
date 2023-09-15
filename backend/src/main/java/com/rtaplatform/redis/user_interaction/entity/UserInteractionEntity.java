package com.rtaplatform.redis.user_interaction.entity;

import com.rtaplatform.user_interaction.model.UserInteraction;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.rtaplatform.utils.DateTimeUtils.getNowTruncatedToMillis;

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
    private String created;
    private String uuid;
    private Long userId;
    @Indexed
    private Long productId;
    private Integer timeSec;

    public static UserInteractionEntity toEntity(UserInteraction model) {
        return UserInteractionEntity.builder()
                .id(model.getId())
                .created(computeIfAbsentCreated(model))
                .uuid(model.getUuid())
                .userId(model.getUserId())
                .productId(model.getProductId())
                .timeSec(model.getTimeSec())
                .build();
    }

    private static String computeIfAbsentCreated(UserInteraction model) {
        return Optional.ofNullable(model.getCreated()).orElse(getNowTruncatedToMillis()).toString();
    }

    public UserInteraction toModel() {
        return UserInteraction.builder()
                .id(id)
                .created(LocalDateTime.parse(created))
                .uuid(uuid)
                .userId(userId)
                .productId(productId)
                .timeSec(timeSec)
                .build();
    }
}
