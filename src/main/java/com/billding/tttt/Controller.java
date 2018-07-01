package com.billding.tttt;

// TODO add terrible Struts 1 dependency
// Consider renaming to Resource/RESTEndpoint/etc.
public class Controller implements UnreliableService {
    private final Logic logic;
    private static final String name = "controller";

    private final int operationRunTime;

    public Controller(int operationRunTime, Logic logic) {
        this.logic = logic;
        this.operationRunTime = operationRunTime;
    }

    // Is the possibility of a null result from this.logic.facilityLevelOperation the last remaining bit to cover?
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
