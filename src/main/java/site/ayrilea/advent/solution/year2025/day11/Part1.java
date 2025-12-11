package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2025.day11.Shared.*;

@SolutionMetadata(year = 2025, day = 11, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        var devices = parseInput(input);

        return pathsBetween(devices.get("you"), devices.get("out"));
    }
}