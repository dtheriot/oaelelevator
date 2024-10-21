package com.theriot.oaelevator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static final String USAGE = "Command line arguments must be provided to indicate start floor and floors " +
            "traveled.\ne.g. java -jar ~.jar start=12 floor=2,9,1,32";
    private static final String START = "start";
    private static final String FLOOR = "floor";

    public static void main(String[] args) {
        try {
            int[] arguments = parseArguments(args);
            OAElevator elevator = new OAElevator(arguments[0], Arrays.copyOfRange(arguments, 1, arguments.length));
            System.out.println(elevator.evaluateResults());
        } catch (Throwable t) {
            System.out.println(USAGE);
        }
    }

    static int[] parseArguments(String[] args) {
        Map<String, String> pairs = collectArguments(args);
        String startExpr = pairs.get(START);
        String floorExpr = pairs.get(FLOOR);
        if (startExpr == null || floorExpr == null) {
            throw new RuntimeException();
        }

        int start = Integer.parseInt(startExpr);
        int[] floors = parseIntList(floorExpr);
        int[] parsedArguments = new int[floors.length + 1];
        parsedArguments[0] = start;
        System.arraycopy(floors, 0, parsedArguments, 1, floors.length);

        return parsedArguments;
    }

    static Map<String, String> collectArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();

        Arrays.stream(args).map(arg -> {
            String[] pair = arg.split("=");
            if (pair.length != 2) {
                throw new RuntimeException();
            }
            return pair;
        }).forEach(pair -> arguments.put(pair[0], pair[1]));

        return arguments;
    }

    static int[] parseIntList(String expr) {
        return Arrays.stream(expr.split(",")).mapToInt(Integer::parseInt).toArray();
    }
}
