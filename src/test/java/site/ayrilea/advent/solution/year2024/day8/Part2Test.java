package site.ayrilea.advent.solution.year2024.day8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 8, Part 2")
public class Part2Test extends AbstractSolutionTest<Long> {

    @DisplayName("Extra example input")
    @Test
    public void extraExampleInput() {
        assertSolution(9L, inputOf("""
                T.........
                ...T......
                .T........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                ..........
                """));
    }

    @Override
    protected Solution<Long> createSolution() {
        return new Part2();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 34L;
    }
}
