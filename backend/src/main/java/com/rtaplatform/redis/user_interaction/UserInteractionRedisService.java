package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.user_interaction.entity.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInteractionRedisService {
    private final UserInteractionRepository userInteractionRepository;

    @Autowired
    public UserInteractionRedisService(UserInteractionRepository userInteractionRepository) {
        this.userInteractionRepository = userInteractionRepository;
    }

    public void addUserInteraction(UserInteraction userInteraction) {
        userInteractionRepository.save(userInteraction);
    }
}
