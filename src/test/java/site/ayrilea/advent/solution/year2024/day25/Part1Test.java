package site.ayrilea.advent.solution.year2024.day25;

import org.junit.jupiter.api.DisplayName;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.year2024.day15.Part1;

@DisplayName("Year 2024, Day 25, Part 1")
public class Part1Test extends AbstractSolutionTest<Integer> {

    @Override
    protected Solution<Integer> createSolution() {
        return new Part1();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 0;
    }
}
