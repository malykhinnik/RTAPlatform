package com.rtaplatform.postgresql;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PostgreSqlUtils {
    public static LocalDateTime getDateTimeNowTruncatedToMillis() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
