package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    static long pathsBetween(Device current, Device end) {
        return pathsBetween(current, end, new HashMap<>());
    }

    private static long pathsBetween(Device current, Device end, Map<Device, Long> cache) {
        if (Objects.equals(current, end)) {
            return 1;
        }

        if (!cache.containsKey(current)) {
            cache.put(current, current.outputs().stream()
                    .map(output -> pathsBetween(output, end, cache))
                    .mapToLong(l -> l)
                    .sum());
        }

        return cache.get(current);
    }
}
