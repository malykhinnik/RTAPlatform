package com.rtaplatform.user_interaction;

import com.rtaplatform.redis.user_interaction.UserInteractionEntityRepository;
import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import com.rtaplatform.user_interaction.model.UserInteraction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserInteractionService {
    private final UserInteractionEntityRepository userInteractionEntityRepository;

    @Autowired
    public UserInteractionService(UserInteractionEntityRepository userInteractionEntityRepository) {
        this.userInteractionEntityRepository = userInteractionEntityRepository;
    }

    public void addUserInteraction(UserInteraction userInteraction) {
        log.info("received user interaction='{}'", userInteraction.toString());
        userInteractionEntityRepository.save(UserInteractionEntity.toEntity(userInteraction));
    }

    public Stream<UserInteraction> streamUsersByProduct(Long productId) {
        return userInteractionEntityRepository.findAllByProductId(productId)
                .stream()
                .map(UserInteractionEntity::toModel);
    }

    public List<UserInteraction> listUsersByProduct(Long productId) {
        return streamUsersByProduct(productId)
                .collect(Collectors.toList());
    }
}
