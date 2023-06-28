package com.kevinsa.security.service.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
public class DataUtils {

    private int calculateInitialDelay(int targetHour) {
        LocalTime now = LocalTime.now();
        int currentHour = now.getHour();
        int delay = targetHour - currentHour;

        if (delay < 0) {
            delay += 24;
        }
        return delay;
    }
}
