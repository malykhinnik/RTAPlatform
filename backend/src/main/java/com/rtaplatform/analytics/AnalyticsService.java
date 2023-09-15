package com.rtaplatform.analytics;

import com.rtaplatform.user_interaction.UserInteractionService;
import com.rtaplatform.user_interaction.model.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnalyticsService {
    private final UserInteractionService userInteractionService;

    @Autowired
    public AnalyticsService(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

    /**
     * Calculating middle user engagement for the product as:
     * sum interaction time / number of unique users
     *
     * @param productId ID of the product
     * @return the value of the calculation rounded to the nearest long value.
     */
    public Long countMiddleUserEngagementByProduct(Long productId) {
        double sumTimeSec = 0.;
        Set<Long> uniqueUsersId = new HashSet<>();
        for (UserInteraction userInteraction : userInteractionService.listUsersByProduct(productId)) {
            sumTimeSec += userInteraction.getTimeSec();
            uniqueUsersId.add(userInteraction.getUserId());
        }

        return Math.round(sumTimeSec / uniqueUsersId.size());
    }

    /**
     * Calculating number of unique users for the product
     *
     * @param productId ID of the product
     * @return the value of the calculation
     */
    public Long countUniqueUsersByProduct(Long productId) {
        return userInteractionService.streamUsersByProduct(productId)
                .map(UserInteraction::getUserId)
                .distinct()
                .count();
    }
}
