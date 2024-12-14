package site.ayrilea.advent.solution.year2024.day14;

import org.junit.jupiter.api.DisplayName;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

@DisplayName("Year 2024, Day 14, Part 2")
public class Part2Test extends AbstractSolutionTest<Long> {

    @Override
    protected Solution<Long> createSolution() {
        return new Part2();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 0L; //Test input doesn't produce Christmas tree
    }
}
