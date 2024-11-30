package site.ayrilea.advent.solution.year2024.day1;

import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

public class Part1Test extends AbstractSolutionTest<String> {

    @Test
    public void placeholder() {
        assertSolution("placeholder", inputOf("placeholder"));
    }

    @Override
    protected Solution<String> createSolution() {
        return new Part1();
    }

    @Override
    protected String exampleExpectedValue() {
        return "example";
    }
}
