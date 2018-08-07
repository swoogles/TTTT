package com.billding.bad_versions;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class TimingFiascos {
    private UsersMapper usersMapper;
    interface UsersMapper {
        List<UUID> getNewUsersFromMapper(Instant cutOff);
    }

    // Relies on timing below.
    public List<UUID> getNewUsers() {
        return usersMapper.getNewUsersFromMapper(
                Instant.now().minus(Duration.ofDays(7))
        );
    }
}
