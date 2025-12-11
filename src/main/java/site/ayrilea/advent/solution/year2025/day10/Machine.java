package site.ayrilea.advent.solution.year2025.day10;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

record Machine(Boolean[] desiredState, List<Boolean[]> buttons, int[] desiredJoltage) {

    private static final Pattern PATTERN_MACHINE = Pattern.compile(
            "\\[(?<desiredState>[.#]+)] (?<buttons>.+) \\{(?<desiredJoltage>[\\d,]+)}");

    static Machine fromInput(String machine) {
        Matcher matcher = PATTERN_MACHINE.matcher(machine);
        if (matcher.matches()) {
            Boolean[] desiredState = parseDesiredState(matcher.group("desiredState"));
            List<Boolean[]> buttons = parseButtons(matcher.group("buttons"), desiredState.length);
            int[] desiredJoltage = parseDesiredJoltage(matcher.group("desiredJoltage"));
            return new Machine(desiredState, buttons, desiredJoltage);
        }
        throw new IllegalArgumentException("Invalid input machine: " + machine);
    }

    private static Boolean[] parseButton(String button, int numberOfLights) {
        Boolean[] parsedButton = new Boolean[numberOfLights];
        IntStream.range(0, numberOfLights).forEach(i -> parsedButton[i] = false);

        Arrays.stream(button.substring(1, button.length() - 1)
                .split(","))
                .map(Integer::parseInt)
                .forEach(i -> parsedButton[i] = true);

        return parsedButton;
    }

    private static List<Boolean[]> parseButtons(String buttons, int numberOfLights) {
        return Arrays.stream(buttons.split(" "))
                .map(button -> parseButton(button, numberOfLights))
                .toList();
    }

    private static int[] parseDesiredJoltage(String desiredJoltage) {
        return Arrays.stream(desiredJoltage.split(","))
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();
    }

    private static Boolean[] parseDesiredState(String desiredState) {
        return desiredState.chars()
                .boxed()
                .map(c -> c == '#')
                .toArray(Boolean[]::new);
    }
}
