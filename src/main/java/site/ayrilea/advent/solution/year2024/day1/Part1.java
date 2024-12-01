package site.ayrilea.advent.solution.year2024.day1;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.stream.IntStream;

import static site.ayrilea.advent.solution.year2024.day1.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 1, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        LocationLists locationLists = parseInput(input);

        return IntStream.range(0, locationLists.getFirstLocationIds().size())
                .map(index -> {
                    int firstLocationId = locationLists.getFirstLocationIds().get(index);
                    int secondLocationId = locationLists.getSecondLocationIds().get(index);
                    return Math.abs(firstLocationId - secondLocationId);
                })
                .sum();
    }
}
