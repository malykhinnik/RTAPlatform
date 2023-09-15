package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInteractionEntityRepository extends CrudRepository<UserInteractionEntity, Long> {
    List<UserInteractionEntity> findAllByProductId(Long productId);
}
