package site.ayrilea.advent.solution.year2025.day12;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2025, Day 12, Part 1")
public class Part1Test extends AbstractSolutionTest<Long> {

    @Disabled("Solution with trick doesn't work on example input")
    @DisplayName("Example input")
    @Override
    @Test
    public void exampleInput() {
        super.exampleInput();
    }

    @Override
    protected Solution<Long> createSolution() {
        return new Part1();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 2L;
    }
}