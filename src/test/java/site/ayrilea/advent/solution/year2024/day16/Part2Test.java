package site.ayrilea.advent.solution.year2024.day16;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.solution.AbstractSolutionTest;
import site.ayrilea.advent.solution.Solution;

import static site.ayrilea.advent.input.TestInput.inputOf;

@DisplayName("Year 2024, Day 16, Part 2")
public class Part2Test extends AbstractSolutionTest<Integer> {

    @DisplayName("Extra example input")
    @Test
    public void extraExampleInput() {
        assertSolution(64, inputOf("""
                #################
                #...#...#...#..E#
                #.#.#.#.#.#.#.#.#
                #.#.#.#...#...#.#
                #.#.#.#.###.#.#.#
                #...#.#.#.....#.#
                #.#.#.#.#.#####.#
                #.#...#.#.#.....#
                #.#.#####.#.###.#
                #.#.#.......#...#
                #.#.###.#####.###
                #.#.#...#.....#.#
                #.#.#.#####.###.#
                #.#.#.........#.#
                #.#.#.#########.#
                #S#.............#
                #################
                """));
    }

    @Override
    protected Solution<Integer> createSolution() {
        return new Part2();
    }

    @Override
    protected Integer exampleExpectedValue() {
        return 45;
    }
}
