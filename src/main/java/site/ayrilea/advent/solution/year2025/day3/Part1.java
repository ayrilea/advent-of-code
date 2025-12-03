package site.ayrilea.advent.solution.year2025.day3;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SolutionMetadata(year = 2025, day = 3, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, 2);
    }
}