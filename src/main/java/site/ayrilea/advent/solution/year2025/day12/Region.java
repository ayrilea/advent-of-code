package site.ayrilea.advent.solution.year2025.day12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

record Region(int length, int width, int totalPresentArea) {

    private static final Pattern PATTERN_REGION = Pattern.compile(
            "(?<width>\\d+)x(?<length>\\d+): (?<requiredPresents>[\\d ]+)");

    static Region fromInput(String region, Map<Integer, Present> presents) {
        Matcher matcher = PATTERN_REGION.matcher(region);
        if (matcher.matches()) {
            int length = Integer.parseInt(matcher.group("length"));
            int width = Integer.parseInt(matcher.group("width"));
            List<Integer> presentCounts = parseRequiredPresents(matcher.group("requiredPresents"));
            return new Region(length, width, totalPresentArea(presentCounts, presents));
        }
        throw new IllegalArgumentException("Invalid input region: " + region);
    }

    private static List<Integer> parseRequiredPresents(String presentCounts) {
        return Arrays.stream(presentCounts.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    private static int totalPresentArea(List<Integer> presentCounts, Map<Integer, Present> presents) {
        return IntStream.range(0, presentCounts.size())
                .map(presentIndex -> presents.get(presentIndex).area() * presentCounts.get(presentIndex))
                .sum();
    }
}
