package site.ayrilea.advent.solution.year2024.day18;

import org.junit.jupiter.api.DisplayName;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2024, Day 18, Part 2")
public class Part2Test extends AbstractSolutionTest<String> {

    @Override
    protected Solution<String> createSolution() {
        return new Part2();
    }

    @Override
    protected String exampleExpectedValue() {
        return "6,1";
    }
}
