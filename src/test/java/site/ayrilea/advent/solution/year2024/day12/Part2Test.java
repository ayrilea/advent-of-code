package site.ayrilea.advent.solution.year2024.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 12, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @DisplayName("Extra example input - 1")
    @Test
    public void extraExampleInputOne() {
        assertSolution(80, inputOf("""
                AAAA
                BBCD
                BBCC
                EEEC
                """));
    }

    @DisplayName("Extra example input - 2")
    @Test
    public void extraExampleInputTwo() {
        assertSolution(436, inputOf("""
                OOOOO
                OXOXO
                OOOOO
                OXOXO
                OOOOO
                """));
    }

    @DisplayName("Extra example input - 3")
    @Test
    public void extraExampleInputThree() {
        assertSolution(236, inputOf("""
                EEEEE
                EXXXX
                EEEEE
                EXXXX
                EEEEE
                """));
    }

    @DisplayName("Extra example input - 4")
    @Test
    public void extraExampleInputFour() {
        assertSolution(368, inputOf("""
                AAAAAA
                AAABBA
                AAABBA
                ABBAAA
                ABBAAA
                AAAAAA
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 1206;
    }
}
