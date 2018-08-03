package com.billding.meta;

import com.billding.tttt.UnreliableService;

import java.time.Duration;

public class SlowTestExecution {
    // Property for whether to use a real delay or not?
    public static Duration executeWithRunTime(UnreliableService unreliableService) {
        Duration duration = unreliableService.failableAction();
        if (DemoScenarios.shouldUseRealRuntimes()) {
            try {
                Thread.sleep(
                        duration.toMillis()
                );
                return duration;
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

        return duration;
    }
}
