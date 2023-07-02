package com.kevinsa.security.service.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@Component
public class DataUtils {

    public long calculateInitialDelay(int targetHour, int targetMinutes, int periodHours) {
        LocalTime currentTime = LocalTime.now();
        LocalTime targetTime = LocalTime.of(targetHour, targetMinutes);
        Duration duration = Duration.between(currentTime, targetTime);
        if (duration.isNegative()) {
            duration = duration.plusHours(periodHours);
        }
        return duration.getSeconds();
    }
}
