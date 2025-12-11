package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Shared {

    private static final Pattern PATTERN_DEVICE = Pattern.compile(
            "(?<label>[a-z]+): (?<outputs>[a-z ]+)");

    static Map<String, Device> parseInput(Input input) {
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
