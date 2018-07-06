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

    public List<Integer> facilityLevelOperation(String facilityId, int numPatients) {
        return patientIdsFor(facilityId, numPatients)
            .stream()
            .map(
                // TODO update this with meaningful result
                patientId -> (int) this.mapper.CRUD_query().toMillis()
            ).collect(Collectors.toList());
    }

    private List<String> patientIdsFor(String facilityId, int numPatients) {
        return IntStream.range(0, numPatients) .mapToObj( i -> facilityId + "_patient_" + i).collect(Collectors.toList());
    }

    @Override
    public Duration getOperationRunTime() {
        // TODO All of these should be calling .getOperationRunTime() for *each* of their dependencies!
        // Every single UnreliableService
        return this.operationRunTime;
    }

    @Override
    public Duration failableAction() {
        return
            this.getOperationRunTime()
            .plus(this.mapper.failableAction());
    }
}
