package site.ayrilea.advent.solution.year2024.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 12, Part 1")
public class Part1Test extends AbstractSolutionTest<Integer> {

    @DisplayName("Extra example input - 1")
    @Test
    public void extraExampleInputOne() {
        assertSolution(140, inputOf("""
                AAAA
                BBCD
                BBCC
                EEEC
                """));
    }

    @DisplayName("Extra example input - 2")
    @Test
    public void extraExampleInputTwo() {
        assertSolution(772, inputOf("""
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part1();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 1930;
    }
}
