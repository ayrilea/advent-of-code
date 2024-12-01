package site.ayrilea.advent.solution.year2024.day1;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static site.ayrilea.advent.solution.year2024.day1.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 1, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        LocationLists locationLists = parseInput(input);

        List<Integer> firstLocationIds = locationLists.getFirstLocationIds();
        Map<Integer, Long> countBySecondLocationId = locationLists.getSecondLocationIds()
                .stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        return firstLocationIds.stream()
                .map(id -> id * countBySecondLocationId.getOrDefault(id, 0L))
                .mapToInt(Long::intValue)
                .sum();
    }
}
