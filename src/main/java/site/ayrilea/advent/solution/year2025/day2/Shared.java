package site.ayrilea.advent.solution.year2025.day2;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Shared {

    static long solve(Input input, Predicate<Long> isInvalid) {
        return parseInput(input.string())
                .flatMap(Range::getIds)
                .filter(isInvalid)
                .mapToLong(i -> i)
                .sum();
    }

    private static Stream<Range> parseInput(String input) {
        return Arrays.stream(input.split(","))
                .map(Range::fromInput);
    }
}
