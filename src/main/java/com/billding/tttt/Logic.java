package com.billding.tttt;

import com.billding.meta.ChaoticWorld;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Logic implements UnreliableService {
    private final ChaoticWorld chaoticWorld;
    private final Mapper mapper;
    private static final String name = "logic";

    private final int operationRunTime;

    public Logic(ChaoticWorld chaoticWorld, Mapper mapper, int operationRunTime) {
        this.chaoticWorld = chaoticWorld;
        this.mapper = mapper;
        this.operationRunTime = operationRunTime;
    }

    public List<Integer> facilityLevelOperation(String facilityId, int numPatients) {
        return patientIdsFor(facilityId, numPatients)
            .stream()
            .map(
                patientId -> this.mapper.CRUD_query()
            ).collect(Collectors.toList());
    }

    private List<String> patientIdsFor(String facilityId, int numPatients) {
        return IntStream.range(0, numPatients) .mapToObj( i -> facilityId + "_patient_" + i).collect(Collectors.toList());
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        return
            this.getOperationRunTime()
            + this.mapper.failableAction();
    }
}
