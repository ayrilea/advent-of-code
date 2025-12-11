package site.ayrilea.advent.solution.year2025.day11;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2025, Day 11, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @Disabled("Test input is different between Part 1 and Part 2")
    @DisplayName("Example input from Part 1 (not valid)")
    @Override
    @Test
    public void exampleInput() {
        super.exampleInput();
    }

    @DisplayName("Example input)")
    @Test
    public void exampleInputForPart2() {
        assertSolution(2, inputOf("""
                svr: aaa bbb
                aaa: fft
                fft: ccc
                bbb: tty
                tty: ccc
                ccc: ddd eee
                ddd: hub
                hub: fff
                eee: dac
                dac: fff
                fff: ggg hhh
                ggg: out
                hhh: out
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 0;
    }
}