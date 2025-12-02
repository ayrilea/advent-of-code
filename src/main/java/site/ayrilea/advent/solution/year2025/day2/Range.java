package site.ayrilea.advent.solution.year2025.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import java.util.stream.Stream;

record Range(long firstId, long lastId) {

    private static final Pattern PATTERN_RANGE = Pattern.compile("(?<firstId>\\d+)-(?<lastId>\\d+)");

    Stream<Long> getIds() {
        return LongStream.rangeClosed(firstId(), lastId())
                .boxed();
    }

    static Range fromInput(String range) {
        Matcher matcher = PATTERN_RANGE.matcher(range);
        if (matcher.matches()) {
            long firstId = Long.parseLong(matcher.group("firstId"));
            long lastId = Long.parseLong(matcher.group("lastId"));
            return new Range(firstId, lastId);
        }
        throw new IllegalArgumentException("Invalid input range: " + range);
    }
}
