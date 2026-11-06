package com.bugtracking.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MailServiceHealth implements HealthIndicator {
    
    @Override
    public Health health() {
        // Check if the mail service is available
        if (isMailServiceAvailable()) {
            // Return an 'UP' status with a detail message
            return Health.up().withDetail("Mail Service", "Mail service is up").build();
        } else {
            // Return a 'DOWN' status with a detail message
            return Health.down().withDetail("Mail Service", "Mail service is down").build();
        }
    }

    // Simulate the logic to check if the mail service is available
    private boolean isMailServiceAvailable() {
        // In a real scenario, you would write logic to connect to the mail microservice
        // and determine its availability. Here, we return 'true' as a placeholder.
        return true;
    }
}
