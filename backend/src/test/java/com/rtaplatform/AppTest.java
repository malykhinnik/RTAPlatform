package com.rtaplatform;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testAppHealth() {
        assertEquals("RTAPlatform start ...", App.health());
    }
}