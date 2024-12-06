package site.ayrilea.advent.solution.year2024.day6;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import static site.ayrilea.advent.solution.year2024.day6.LabMap.parseInput;

@SolutionMetadata(year = 2024, day = 6, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return parseInput(input).getNewObstructionsCausingALoopCount();
    }
}