package site.ayrilea.advent.solution.year2024.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Arrays;

@SolutionMetadata(year = 2024, day = 11, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        StoneTransformer transformer = StoneTransformer.forBlinks(75);
        return Arrays.stream(input.string()
                        .split(" "))
                .map(Long::parseLong)
                .map(transformer::getNumberOfStones)
                .mapToLong(l -> l)
                .sum();
    }
}