package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

@SolutionMetadata(year = 2025, day = 11, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Map<String, Device> devices = Shared.parseInput(input);
        return numberOfPaths(devices.get("out"), devices.get("you"));
    }

    private static int numberOfPaths(Device end, Device current) {
        if (Objects.equals(current, end)) {
            return 1;
        }

        int paths = 0;
        for (Device output : current.outputs()) {
            paths += numberOfPaths(end, output);
        }
        return paths;
    }
}