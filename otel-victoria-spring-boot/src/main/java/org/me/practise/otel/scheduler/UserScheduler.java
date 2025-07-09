package org.me.practise.otel.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.me.practise.otel.service.CustomUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserScheduler {

    private final CustomUserService userService;

    @Scheduled(fixedRate = 300000) // Every 5 minutes
    //@SchedulerLock(name = "userStatsScheduler", lockAtMostFor = "PT4M", lockAtLeastFor = "PT1M")
    public void logUserStats() {
        log.info("Running scheduled user stats task...");
        try {
            int totalUsers = userService.getAllUsers().size();
            log.info("Total users in system: {}", totalUsers);
        } catch (Exception e) {
            log.error("Error in scheduled user stats task", e);
        }
    }
}

