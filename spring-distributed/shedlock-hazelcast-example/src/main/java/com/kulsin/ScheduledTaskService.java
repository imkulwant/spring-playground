package com.kulsin;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class ScheduledTaskService {

    @Scheduled(fixedRateString = "PT30S")
    @SchedulerLock(name = "scheduledTask", lockAtLeastFor = "PT10S", lockAtMostFor = "PT30S")
    public void scheduledTask() {

        log.info("Scheduled task started!");

        try {

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            log.error("InterruptedException occurred", e);
        }

        log.info("Scheduled task finished!");

    }

}
