package site.ayrilea.advent.solution.year2025.day1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Shared {

    private static final Pattern PATTERN_ROTATION = Pattern.compile("(?<direction>[LR])(?<amount>\\d{1,3})");

    static int parseLine(String line) {
        Matcher matcher = PATTERN_ROTATION.matcher(line);
        if (matcher.matches()) {
            int directionMultiplier = parseDirectionMultiplier(matcher.group("direction"));
            int amount = Integer.parseInt(matcher.group("amount"));
            return directionMultiplier * amount;
        }
        throw new IllegalArgumentException("Invalid input line: " + line);
    }

    private static int parseDirectionMultiplier(String direction) {
        return switch (direction) {
            case "L" -> -1;
            case "R" -> 1;
            default -> throw new IllegalArgumentException("Invalid direction: " + direction);
        };
    }
}
