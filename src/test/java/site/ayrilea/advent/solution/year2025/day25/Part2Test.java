package site.ayrilea.advent.solution.year2025.day25;

import org.junit.jupiter.api.DisplayName;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2025, Day 25, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 0;
    }
}