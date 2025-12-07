package site.ayrilea.advent.solution.year2025.day7;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

@SolutionMetadata(year = 2025, day = 7, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Diagram diagram = Diagram.fromInput(input);
        diagram.simulateBeam();;
        return diagram.getSplitCount();
    }
}