package site.ayrilea.advent.solution.year2025.day8;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;
import java.util.Set;

import static site.ayrilea.advent.solution.year2025.day8.Shared.*;
import static site.ayrilea.advent.solution.year2025.day8.Shared.parseInput;

@SolutionMetadata(year = 2025, day = 8, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        ParsedInput parsedInput = parseInput(input);

        List<Pair> closestPairs = getClosestPairs(parsedInput, false);
        List<Set<Junction>> circuits = getStartingCircuits(parsedInput);

        Pair current;
        do {
            current = closestPairs.removeFirst();
            connectPair(circuits, current);
        } while (circuits.size() > 1);

        return current.first().x() * current.second().x();
    }
}