package com.rtaplatform.kafka.user_interaction.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rtaplatform.user_interaction.model.UserInteraction;
import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserInteractionMessage {
    private Long created;
    private String uuid;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("time_sec")
    private Integer timeSec;

    public UserInteraction toUserInteraction() {
        return UserInteraction.builder()
                .created(created)
                .uuid(uuid)
                .userId(userId)
                .productId(productId)
                .timeSec(timeSec)
                .build();
    }
}
