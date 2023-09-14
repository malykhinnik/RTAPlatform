package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.user_interaction.entity.UserInteraction;
import org.springframework.data.repository.CrudRepository;

public interface UserInteractionRepository extends CrudRepository<UserInteraction, Long> {
}
