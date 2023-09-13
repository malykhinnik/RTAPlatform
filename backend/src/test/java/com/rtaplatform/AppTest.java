package com.rtaplatform;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testAppHealth() {
        assertEquals("RTAPlatform start ...", App.health());
    }
}