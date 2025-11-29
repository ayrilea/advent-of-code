package site.ayrilea.advent.solution.year2024.day19;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2024, Day 19, Part 1")
public class Part1Test extends AbstractSolutionTest<Integer> {

    @Disabled("Solution not implemented")
    @DisplayName("Example input")
    @Override
    @Test
    public void exampleInput() {
        super.exampleInput();
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part1();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 6;
    }
}
