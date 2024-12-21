package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

@SolutionMetadata(year = 2024, day = 16, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part2::getNumberOfNodesVisitedInAllShortestPaths);
    }

    private static int getNumberOfNodesVisitedInAllShortestPaths(List<Node> solutions) {
        return (int) solutions.stream()
                .map(Node::getPath)
                .flatMap(List::stream)
                .distinct()
                .count();
    }
}