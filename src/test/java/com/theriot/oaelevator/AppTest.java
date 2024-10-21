package com.theriot.oaelevator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testParseArguments() {
        assertThrows(
                RuntimeException.class, () -> {
                    App.parseArguments(new String[]{"baseball"});
                }
        );
    }

    @Test
    void testParseArguments2() {
        int[] values = App.parseArguments(new String[]{"start=1", "floor=2,3,4,5"});
        assertTrue(Arrays.equals(values, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testCollectArguments() {
        Map<String, String> args = App.collectArguments(new String[]{"yo=4", "baseball=4"});
        assertEquals(args.size(), 2);
    }

    @Test
    void testCollectArguments2() {
        assertThrows(
                RuntimeException.class, () -> {
                    App.collectArguments(new String[]{"yo", "baseball"});
                });
    }

    @Test
    void testParseIntList() {
        assertEquals(3, App.parseIntList("1,2,3").length);
    }

    @Test
    void testParseIntList2() {
        assertThrows(
                RuntimeException.class,
                () -> App.parseIntList("I am a little teapot, short and stout")
        );
    }
}
