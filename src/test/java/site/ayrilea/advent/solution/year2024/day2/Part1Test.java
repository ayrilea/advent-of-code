package site.ayrilea.advent.solution.year2024.day2;

import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

public class Part1Test extends AbstractSolutionTest<Long> {

    @Override
    protected Solution<Long> createSolution() {
        return new Part1();
    }

    @Override
    protected Long exampleExpectedValue() {
        return 2L;
    }
}
