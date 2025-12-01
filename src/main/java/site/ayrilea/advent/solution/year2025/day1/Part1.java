package site.ayrilea.advent.solution.year2025.day1;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;

@SolutionMetadata(year = 2025, day = 1, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        int count = 0;
        List<Integer> rotations = input.stream().map(Shared::parseLine).toList();

        int value = 50;
        for (int rotation : rotations) {
            value += rotation;
            while (value > 99) {
                value = value - 100;
            }
            while (value < 0) {
                value = value + 100;
            }
            if (value == 0) {
                count++;
            }
        }

        return count;
    }
}