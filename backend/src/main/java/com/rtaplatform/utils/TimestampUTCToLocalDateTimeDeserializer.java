package com.rtaplatform.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampUTCToLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    @SuppressWarnings("unused")
    public TimestampUTCToLocalDateTimeDeserializer() {
        this(null);
    }

    public TimestampUTCToLocalDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final long value = jsonParser.getLongValue();
        final Instant epochSecond = Instant.ofEpochSecond(value, 0);
        return LocalDateTime.ofInstant(
                epochSecond,
                ZoneOffset.UTC);
    }
}
