package com.billding.tttt;

import java.util.List;

// TODO add terrible Struts 1 dependency
public class Controller implements UnreliableService {
    private final Logic logic;
    private static final String name = "controller";

    private final int operationRunTime;

    public Controller(int operationRunTime, Logic logic) {
        this.logic = logic;
        this.operationRunTime = operationRunTime;
    }

    public int facilityLevelOperation(String facilityId, int numPatients) {
        return
            this.logic.facilityLevelOperation(facilityId, numPatients)
            .stream().reduce(0, (a,b) -> a + b)
            ;
    }

    @Override
    public int getOperationRunTime() {
        return this.operationRunTime;
    }

    @Override
    public int failableAction() {
        return
            this.getOperationRunTime()
            + this.logic.failableAction();
    }
}
