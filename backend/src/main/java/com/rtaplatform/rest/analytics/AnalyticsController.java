package com.rtaplatform.rest.analytics;

import com.rtaplatform.analytics.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("analytics")
@RequestMapping(value = "/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(final AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("middle-user-engagement")
    public Long middleUserEngagement(@RequestParam(name="product-id") final Long productId) {
        return analyticsService.middleUserEngagement(productId);
    }

    @GetMapping("number-of-unique-users")
    public Long numberOfUniqueUsers(@RequestParam(name="product-id") final Long productId) {
        return analyticsService.numberOfUniqueUsers(productId);
    }
}
