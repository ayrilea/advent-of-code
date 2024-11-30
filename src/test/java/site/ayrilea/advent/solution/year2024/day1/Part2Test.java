package site.ayrilea.advent.solution.year2024.day1;

import org.junit.jupiter.api.Test;
import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.input.TestInput;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

public class Part2Test extends AbstractSolutionTest<String> {

    @Test
    public void placeholder() {
        Input input = new TestInput.Builder()
                .line("placeholder")
                .build();

        assertSolution("placeholder", input);
    }

    @Override
    protected Solution<String> createSolution() {
        return new Part2();
    }

    @Override
    protected String exampleExpectedValue() {
        return "example";
    }
}
