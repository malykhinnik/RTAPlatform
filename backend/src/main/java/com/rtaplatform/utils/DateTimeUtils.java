package com.rtaplatform.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
    public static LocalDateTime getNowTruncatedToMillis() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
