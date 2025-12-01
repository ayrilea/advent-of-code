package site.ayrilea.advent.solution.year2025.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2025, Day 1, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @DisplayName("Left one full rotation")
    @Test
    public void leftOneFullRotation() {
        assertSolution(1, inputOf("""
                L100
                """));
    }

    @DisplayName("Left two full rotation")
    @Test
    public void leftTwoFullRotations() {
        assertSolution(2, inputOf("""
                L200
                """));
    }

    @DisplayName("Left passing zero")
    @Test
    public void leftPassingZero() {
        assertSolution(1, inputOf("""
                L51
                """));
    }

    @DisplayName("Left reaching 0")
    @Test
    public void leftReachingZero() {
        assertSolution(1, inputOf("""
                L50
                """));
    }

    @DisplayName("Left reaching 0 then left non 0")
    @Test
    public void leftZeroThenNonZero() {
        assertSolution(1, inputOf("""
                L50
                L1
                """));
    }

    @DisplayName("Right one full rotation")
    @Test
    public void rightOneFullRotation() {
        assertSolution(1, inputOf("""
                R100
                """));
    }

    @DisplayName("Right one full rotation")
    @Test
    public void rightTwoFullRotations() {
        assertSolution(2, inputOf("""
                R200
                """));
    }

    @DisplayName("Right passing zero")
    @Test
    public void rightPassingZero() {
        assertSolution(1, inputOf("""
                R51
                """));
    }

    @DisplayName("Right reaching 0")
    @Test
    public void rightReachingZero() {
        assertSolution(1, inputOf("""
                R50
                """));
    }

    @DisplayName("Right reaching 0 then left non 0")
    @Test
    public void rightZeroThenNonZero() {
        assertSolution(1, inputOf("""
                R50
                L1
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 6;
    }
}