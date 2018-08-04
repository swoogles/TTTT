package com.billding.tttt;

import com.billding.meta.ServiceStatus;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractUnreliableService implements UnreliableService {
    private final List<UnreliableService> services;
    private final Optional<String> requiredService;
    private final Duration runtime;
    // TODO make Optional<ChaoticWorld> ? This might belong inside implementations.
    // or maybe another abstract class below this one?

    public AbstractUnreliableService(String requiredService, Duration runtime, UnreliableService service) {
        this.requiredService = Optional.ofNullable(requiredService);
        this.runtime = runtime;
        this.services = Collections.singletonList(service);
    }

    public AbstractUnreliableService(String requiredService, Duration runtime, UnreliableService... services) {
        this.runtime = runtime;
        this.services = Arrays.asList(services);
        this.requiredService = Optional.ofNullable(requiredService);
    }

    @Override
    public Duration getRunTime() {
        this.requiredService.ifPresent(ServiceStatus::ensureServiceIsRunning);
        return
                this.services.stream()
                .map(UnreliableService::getRunTime)
                .reduce(this.runtime, Duration::plus);
    }


    @Override
    public Duration fallibleAction() {
        // TODO reinstate this?
//        this.chaoticWorld.currentTime();
        this.requiredService.ifPresent(ServiceStatus::ensureServiceIsRunning);
        return this.services.stream()
                .map(UnreliableService::fallibleAction)
                // TODO I thought should be using this instead, but I think I'm wrong now.
//                .reduce(this.fallibleAction(), Duration::plus);
                .reduce(this.runtime, Duration::plus);
    }
}
