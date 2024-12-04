package site.ayrilea.advent.solution.year2024.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 4, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @DisplayName("One cross - DownRight X UpRight")
    @Test
    public void crossDownRightUpRight() {
        assertSolution(1, inputOf("""
                MXS
                XAX
                MXS
                """));
    }

    @DisplayName("One cross - UpLeft X DownLeft")
    @Test
    public void crossUpLeftDownLeft() {
        assertSolution(1, inputOf("""
                SXM
                XAX
                SXM
                """));
    }

    @DisplayName("One cross - DownRight X DownLeft")
    @Test
    public void crossDownRightDownLeft() {
        assertSolution(1, inputOf("""
                MXM
                XAX
                SXS
                """));
    }

    @DisplayName("One cross - UpRight X UpLeft")
    @Test
    public void crossUpRightUpLeft() {
        assertSolution(1, inputOf("""
                SXS
                XAX
                MXM
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 9;
    }
}
