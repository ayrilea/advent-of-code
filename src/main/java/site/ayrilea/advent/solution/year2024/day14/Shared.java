package site.ayrilea.advent.solution.year2024.day14;

import site.ayrilea.advent.input.Input;

import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Shared {

    private static final Pattern PATTERN_MAX_SIZE = Pattern.compile(
            "(?<xMax>\\d+),(?<yMax>\\d+)");

    static long solve(Input input, BiFunction<Stream<Robot>, Bounds, Long> processRobots) {
        List<String> lines = input.list();

        Bounds bounds = parseBounds(lines.getFirst());
        Stream<Robot> robots = parseRobots(lines.subList(1, lines.size()), bounds.xMax(), bounds.yMax());

        return processRobots.apply(robots, bounds);
    }

    private static Bounds parseBounds(String line) {
        Matcher matcher = PATTERN_MAX_SIZE.matcher(line);
        if (matcher.matches()) {
            int xMax = Integer.parseInt(matcher.group("xMax"));
            int yMax = Integer.parseInt(matcher.group("yMax"));;
            return new Bounds(xMax, yMax);
        }
        throw new IllegalArgumentException("Invalid line: " + line);
    }

    static Stream<Robot> parseRobots(List<String> robotLines, int xMax, int yMax) {
        return robotLines.stream()
                .map(line -> Robot.fromLine(line, xMax, yMax));
    }
}
