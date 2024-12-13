package site.ayrilea.advent.solution.year2024.day13;

import site.ayrilea.advent.input.Input;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Gatherers.windowFixed;

class Shared {

    static Long solve(Input input, long base) {
        return input.stream()
                .gather(windowFixed(4))
                .map(lines -> ClawMachine.fromInputLines(lines, base))
                .map(Shared::minimumTokensForPrize)
                .mapToLong(Long::longValue)
                .sum();
    }

    private static long minimumTokensForPrize(ClawMachine machine) {
        return solveSimultaneousEquations(
                machine.a().xMove(), machine.b().xMove(),
                machine.a().yMove(), machine.b().yMove(),
                machine.xTarget(), machine.yTarget());
    }

    /**
     * Solve linear simultaneous equation.
     * <p>
     * buttonA.xMove * aPresses + buttonB.xMove * bPresses = machine.xTarget
     * buttonA.yMove * aPresses + buttonB.yMove * bPresses = machine.yTarget
     *
     * @return 3*aPresses + bPresses if solutions are mathematical integer values, else 0
     */
    private static long solveSimultaneousEquations(long aXMove, long bXMove, long aYMove, long bYMove, long xTarget,
                                                   long yTarget) {
        double det = ((aXMove) * (bYMove) - (bXMove) * (aYMove));
        double aPresses = ((bYMove) * (xTarget) - (bXMove) * (yTarget)) / det;
        double bPresses = ((aXMove) * (yTarget) - (aYMove) * (xTarget)) / det;

        if (aPresses == Math.rint(aPresses) && bPresses == Math.rint(bPresses)) {
            return (long) Math.rint(aPresses * 3 + bPresses);
        }
        return 0;
    }

    private record Button(long xMove, long yMove) {

        private static final Pattern PATTERN_BUTTON = Pattern.compile(
                "Button [AB]: X\\+(?<xMove>\\d+), Y\\+(?<yMove>\\d+)");

        private static Button fromInputLine(String line) {
            Matcher matcher = PATTERN_BUTTON.matcher(line);
            if (matcher.matches()) {
                return new Button(Long.parseLong(matcher.group("xMove")), Long.parseLong(matcher.group("yMove")));
            }
            throw new IllegalArgumentException("Invalid input line: " + line);
        }
    }

    private record ClawMachine(Button a, Button b, long xTarget, long yTarget) {

        private static final Pattern PATTERN_PRIZE = Pattern.compile(
                "Prize: X=(?<xTarget>\\d+), Y=(?<yTarget>\\d+)");

        private static ClawMachine fromInputLines(List<String> lines, long base) {
            Button buttonA = Button.fromInputLine(lines.getFirst());
            Button buttonB = Button.fromInputLine(lines.get(1));

            Matcher matcher = PATTERN_PRIZE.matcher(lines.get(2));
            if (matcher.matches()) {
                long xTarget = Long.parseLong(matcher.group("xTarget")) + base;
                long yTarget = Long.parseLong(matcher.group("yTarget")) + base;

                return new ClawMachine(buttonA, buttonB, xTarget, yTarget);
            }
            throw new IllegalArgumentException("Invalid input line: " + lines.get(2));
        }
    }
}
