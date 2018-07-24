package com.billding.meta;

import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class SlowTestExecution {
    public static Duration executeWithRunTime(UnreliableService unreliableService) {
        try {
            Duration duration = unreliableService.failableAction();
            Thread.sleep(
                    duration.toMillis()
            );
            return duration;
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
