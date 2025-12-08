package site.ayrilea.advent.solution.year2025.day8;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;
import java.util.stream.Collectors;

import static site.ayrilea.advent.solution.year2025.day8.Shared.*;
import static site.ayrilea.advent.solution.year2025.day8.Shared.connectPair;

@SolutionMetadata(year = 2025, day = 8, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        ParsedInput parsedInput = parseInput(input);

        List<Pair> closestPairs = getClosestPairs(parsedInput, true);
        List<Set<Junction>> circuits = getStartingCircuits(parsedInput);

        for (Pair pair : closestPairs) {
            connectPair(circuits, pair);
        }

        return circuits.stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }
}