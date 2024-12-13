package site.ayrilea.advent.solution.year2024.day13;

import site.ayrilea.advent.input.Input;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Gatherers.windowFixed;

class Shared {

    private static final int MAX_BUTTON_PRESSES = 100;

    static Integer solve(Input input) {
        return input.stream()
                .gather(windowFixed(4))
                .map(ClawMachine::fromInputLines)
                .map(Shared::minimumTokensForPrize)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static int minimumTokensForPrize(ClawMachine machine) {
        Set<Integer> solutions = new HashSet<>();

        for (int aPresses = 0; aPresses <= MAX_BUTTON_PRESSES; aPresses++) {
            for (int bPresses = 0; bPresses <= MAX_BUTTON_PRESSES; bPresses++) {
                int x = aPresses * machine.a().xMove() + bPresses * machine.b().xMove();
                int y = aPresses * machine.a().yMove() + bPresses * machine.b().yMove();
                if (x == machine.xTarget() && y == machine.yTarget()) {
                    solutions.add(aPresses * 3 + bPresses);
                }
            }
        }

        return solutions.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(0);
    }

    private record Button (int xMove, int yMove) {

        private static final Pattern PATTERN_BUTTON = Pattern.compile(
                "Button [AB]: X\\+(?<xMove>\\d+), Y\\+(?<yMove>\\d+)");

        private static Button fromInputLine(String line) {
            Matcher matcher = PATTERN_BUTTON.matcher(line);
            if (matcher.matches()) {
                return new Button(Integer.parseInt(matcher.group("xMove")), Integer.parseInt(matcher.group("yMove")));
            }
            throw new IllegalArgumentException("Invalid input line: " + line);
        }
    }

    private record ClawMachine(Button a, Button b, int xTarget, int yTarget) {

        private static final Pattern PATTERN_PRIZE = Pattern.compile(
                "Prize: X=(?<xTarget>\\d+), Y=(?<yTarget>\\d+)");

        private static ClawMachine fromInputLines(List<String> lines) {
            Button buttonA = Button.fromInputLine(lines.getFirst());
            Button buttonB = Button.fromInputLine(lines.get(1));

            Matcher matcher = PATTERN_PRIZE.matcher(lines.get(2));
            if (matcher.matches()) {
                int xTarget = Integer.parseInt(matcher.group("xTarget"));
                int yTarget = Integer.parseInt(matcher.group("yTarget"));

                return new ClawMachine(buttonA, buttonB, xTarget, yTarget);
            }
            throw new IllegalArgumentException("Invalid input line: " + lines.get(2));
        }
    }
}
