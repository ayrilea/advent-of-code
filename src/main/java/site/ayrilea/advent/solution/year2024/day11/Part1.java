package site.ayrilea.advent.solution.year2024.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Arrays;
import java.util.List;

@SolutionMetadata(year = 2024, day = 11, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Arrays.stream(input.string()
                .split(" "))
                .map(Long::parseLong)
                .map(Part1::getNumberOfStones)
                .mapToLong(l -> l)
                .sum();
    }

    private static List<Long> blink(long stone) {
        if (stone == 0) {
            return List.of(1L);
        }
        String digits = String.valueOf(stone);
        if (digits.length() % 2 == 0) {
            long first = Long.parseLong(digits.substring(0, digits.length() / 2));
            long second = Long.parseLong(digits.substring(digits.length() / 2));
            return List.of(first, second);
        }
        return List.of(stone * 2024L);
    }

    private static long getNumberOfStones(long stone) {
        return getNumberOfStones(stone, 0);
    }

    private static long getNumberOfStones(long stone, int blink) {
        if (blink == 25) {
            return 1;
        }
        List<Long> next = blink(stone);
        if (next.size() == 1) {
            return getNumberOfStones(next.getFirst(), blink + 1);
        }
        return getNumberOfStones(next.getFirst(), blink + 1) + getNumberOfStones(next.get(1), blink + 1);
    }
}
