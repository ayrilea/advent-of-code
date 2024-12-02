package site.ayrilea.advent.solution.year2024.day2;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

@SolutionMetadata(year = 2024, day = 2, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return input.stream()
                .map(Report::fromInput)
                .filter(Report::isSafe)
                .count();
    }
}
