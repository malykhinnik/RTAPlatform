package com.rtaplatform.user_interaction;

import com.rtaplatform.redis.user_interaction.UserInteractionRepository;
import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import com.rtaplatform.user_interaction.model.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionService {
    private final UserInteractionRepository userInteractionRepository;

    @Autowired
    public UserInteractionService(UserInteractionRepository userInteractionRepository) {
        this.userInteractionRepository = userInteractionRepository;
    }

    public void addUserInteraction(UserInteraction userInteraction) {
        userInteractionRepository.save(UserInteractionEntity.toEntity(userInteraction));
    }
}
