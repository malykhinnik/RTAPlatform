package com.rtaplatform.kafka;

import org.springframework.util.StringUtils;

public class KafkaUtils {
    public static final String MESSAGE_VALIDATION_REGEX = "[\\s*|\\S\\d\\w]*\"uuid\"\\s*:\\s*\"[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}\"[\\s*|\\S\\d\\w]*";

    public static boolean isValidMessage(String message) {
        return StringUtils.hasLength(message) &&
                message.matches(MESSAGE_VALIDATION_REGEX);
    }

}
