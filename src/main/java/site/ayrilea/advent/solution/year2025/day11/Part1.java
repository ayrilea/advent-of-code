package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SolutionMetadata(year = 2025, day = 11, part = 1)
public class Part1 implements Solution<Integer> {

    private static final Pattern PATTERN_DEVICE = Pattern.compile(
            "(?<label>[a-z]+): (?<outputs>[a-z ]+)");

    @Override
    public Integer solve(Input input) {
        Map<String, Device> devices = parseInput(input);
        return numberOfPaths(devices.get("out"), devices.get("you"));
    }

    private static int numberOfPaths(Device end, Device current) {
        if (Objects.equals(current, end)) {
            return 1;
        }

        int paths = 0;
        for (Device output : current.outputs()) {
            paths += numberOfPaths(end, output);
        }
        return paths;
    }

    private static Map<String, Device> parseInput(Input input) {
        Map<String, Device> devices = new HashMap<>();

        for (String device : input.list()) {
            Matcher matcher = PATTERN_DEVICE.matcher(device);
            if (matcher.matches()) {
                String label = matcher.group("label");
                Device current = devices.computeIfAbsent(label, Device::new);

                Arrays.stream(matcher.group("outputs")
                        .split(" "))
                        .forEach(outputLabel -> {
                            Device output = devices.computeIfAbsent(outputLabel, Device::new);
                            current.outputs().add(output);
                        });
            } else {
                throw new IllegalArgumentException("Invalid input device: " + device);
            }
        }

        return devices;
    }
}