package com.billding.bad_versions;

import com.billding.meta.ChaoticWorld;
import com.billding.tttt.Mapper;
import com.billding.tttt.UnreliableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * Performs more complex operations that involve a {@link Mapper}.
 */
public class FieldInjectedLogic {
    @Autowired
    private ChaoticWorld chaoticWorld;

    @Autowired
    private Mapper mapper;

    // TODO Presentation item - What if I needed 2 Mappers for this class?
//    private Mapper mapper;

    private Duration runtime;

    public Duration failableAction() {
        this.chaoticWorld.currentTime();
        return Stream.of(mapper)
                .map(UnreliableService::failableAction)
                .reduce(this.runtime, Duration::plus);
    }

    public void setRuntime(Duration runtime) {
        this.runtime = runtime;
    }


}
