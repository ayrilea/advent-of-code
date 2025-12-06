package site.ayrilea.advent.solution.year2025.day6;

import org.junit.jupiter.api.DisplayName;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2025, Day 6, Part 1")
public class Part1Test extends AbstractSolutionTest<Long> {

    @Override
    protected Solution<Long> createSolution() {
        return new Part1();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 4277556L;
    }
}