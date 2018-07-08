package com.billding.tttt;

import com.billding.meta.ChaoticWorld;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class Logic implements UnreliableService {
    private final ChaoticWorld chaoticWorld;
    private final Mapper mapper;
    private static final String name = "logic";

    private final Duration operationRunTime;

    public Logic(ChaoticWorld chaoticWorld, Mapper mapper, Duration operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.mapper = mapper;
        this.operationRunTime = operationRunTime;
    }

    public Duration facilityLevelOperation() {
        return this.failableAction();
    }

    @Override
    public Duration getOperationRunTime() {
        return this.operationRunTime
            .plus(this.mapper.getOperationRunTime());
    }

    @Override
    public Duration failableAction() {
        return
            this.getOperationRunTime()
            .plus(this.mapper.failableAction());
    }
}
