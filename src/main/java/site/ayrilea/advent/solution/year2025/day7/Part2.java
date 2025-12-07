package site.ayrilea.advent.solution.year2025.day7;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

@SolutionMetadata(year = 2025, day = 7, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Diagram.fromInput(input).getNumberOfTimelines();
    }
}