package com.theriot.oaelevator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class OAElevatorTest {

    @Test
    void testEvaluateResults() {
        OAElevator elevator = new OAElevator(1, new int[]{2, 3, 4});
        assertEquals(elevator.evaluateResults(), "30 1,2,3,4");
        OAElevator elevator2 = new OAElevator(12, new int[]{2, 9, 1, 32});
        assertEquals(elevator2.evaluateResults(), "560 12,2,9,1,32");
    }

    @Test
    void testCollector() {
        assertEquals(3, Stream.of(1, 2, 3, 4).collect(OAElevator.FloorsTraveledCollector.collector()));
        assertEquals(9, Stream.of(1, 4, 2, 6).collect(OAElevator.FloorsTraveledCollector.collector()));
    }
}
