package site.ayrilea.advent.solution.year2025.day2;

import java.util.Arrays;
import java.util.stream.Stream;

class Shared {

    static Stream<Range> parseInput(String input) {
        return Arrays.stream(input.split(","))
                .map(Range::fromInput);
    }
}
