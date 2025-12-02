package site.ayrilea.advent.solution.year2025.day2;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SolutionMetadata(year = 2025, day = 2, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.parseInput(input.string())
                .map(Part1::invalidIds)
                .flatMap(List::stream)
                .mapToLong(i -> i)
                .sum();
    }

    private static List<Long> invalidIds(Range range) {
        return LongStream.rangeClosed(range.firstId(), range.lastId())
                .filter(Part1::isInvalid)
                .boxed()
                .toList();
    }

    private static boolean isInvalid(long id) {
        String label = Long.toString(id);

        //Odd length IDs are always valid
        if (label.length() % 2 == 1) {
            return false;
        }

        String firstHalf = label.substring(0, label.length() / 2);
        String secondHalf = label.substring(label.length() / 2);

        return Objects.equals(firstHalf, secondHalf);
    }
}