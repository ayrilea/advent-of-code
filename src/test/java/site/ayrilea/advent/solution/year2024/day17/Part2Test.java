package site.ayrilea.advent.solution.year2024.day17;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 17, Part 2")
public class Part2Test extends AbstractSolutionTest<Long> {

    @Disabled("Solution not implemented")
    @DisplayName("Extra example input")
    @Test
    public void extraExampleInput() {
        assertSolution(117440L, inputOf("""
                Register A: 2024
                Register B: 0
                Register C: 0
                
                Program: 0,3,5,4,3,0
                """));
    }

    @Override
    protected Solution<Long> createSolution() {
        return new Part2();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 0L;
    }
}
