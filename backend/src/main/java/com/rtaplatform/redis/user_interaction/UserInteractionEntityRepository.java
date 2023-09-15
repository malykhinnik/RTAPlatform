package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserInteractionEntityRepository extends CrudRepository<UserInteractionEntity, Long> {
}