package com.theriot.oaelevator;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OAElevator {

    private static final int SINGLE_FLOOR_TRAVEL_TIME = 10;

    private int start;
    private int[] floors;

    public OAElevator(int start, int[] floors) {
        this.start = start;
        this.floors = floors;
    }

    public String evaluateResults() {
        // combine floors
        int[] allFloors = new int[floors.length + 1];
        allFloors[0] = start;
        System.arraycopy(floors, 0, allFloors, 1, floors.length);

        // must convert int[] to Integer[] because pure Stream objects work differently than IntStream objects
        Integer[] allFloorsIntegers = Arrays.stream( allFloors ).boxed().toArray( Integer[]::new );

        int totalFloorsTraveled = Stream.of(allFloorsIntegers).collect(FloorsTraveledCollector.collector());
        int totalTime = SINGLE_FLOOR_TRAVEL_TIME * totalFloorsTraveled;
        String floorList = Arrays.stream(allFloors).mapToObj(String::valueOf).collect(Collectors.joining(","));

        return String.format("%d %s", totalTime, floorList);
    }

    static final class FloorsTraveledCollector {

        private int first;
        private int second;
        private int totalFloorsTraveled;

        public void accept(int next) {
            first = second;
            second = next;
            if (first != 0) {
                totalFloorsTraveled += Math.abs(first - second);
            }
        }

        public FloorsTraveledCollector combine(FloorsTraveledCollector other) {
            throw new UnsupportedOperationException("Parallel Stream not supported");
        }

        public int finish() {
            return totalFloorsTraveled;
        }

        public static Collector<Integer, ?, Integer> collector() {
            return Collector.of(FloorsTraveledCollector::new, FloorsTraveledCollector::accept, FloorsTraveledCollector::combine, FloorsTraveledCollector::finish);
        }
    }

    @Override
    public String toString() {
        return "OAElevator{" +
                "start=" + start +
                ", floors=" + Arrays.toString(floors) +
                '}';
    }
}
