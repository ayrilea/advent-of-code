package site.ayrilea.advent.solution.year2024.day4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

public class Part1Test extends AbstractSolutionTest<Integer> {

    @DisplayName("One word - Up Left")
    @Test
    public void wordUpLeft() {
        assertSolution(1, inputOf("""
                SSSS
                SASS
                SSMS
                SSSX
                """));
    }

    @DisplayName("One word - Up")
    @Test
    public void wordUp() {
        assertSolution(1, inputOf("""
                S
                A
                M
                X
                """));
    }

    @DisplayName("One word - Up Right")
    @Test
    public void wordUpRight() {
        assertSolution(1, inputOf("""
                SSSS
                SSAS
                SMSS
                XSSS
                """));
    }

    @DisplayName("One word - Left")
    @Test
    public void wordLeft() {
        assertSolution(1, inputOf("""
                SAMX
                """));
    }

    @DisplayName("One word - Right")
    @Test
    public void wordRight() {
        assertSolution(1, inputOf("""
                XMAS
                """));
    }

    @DisplayName("One word - Down Left")
    @Test
    public void wordDownLeft() {
        assertSolution(1, inputOf("""
                SSSX
                SSMS
                SASS
                SSSS
                """));
    }

    @DisplayName("One word - Down")
    @Test
    public void wordDown() {
        assertSolution(1, inputOf("""
                X
                M
                A
                S
                """));
    }

    @DisplayName("One word - Down Right")
    @Test
    public void wordDownRight() {
        assertSolution(1, inputOf("""
                XSSS
                SMSS
                SSAS
                SSSS
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part1();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 18;
    }
}
