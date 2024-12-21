package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day16.Direction.*;
import static site.ayrilea.advent.solution.year2024.day16.Direction.WEST;
import static site.ayrilea.advent.solution.year2024.day16.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day16.Node.initialNode;

@SolutionMetadata(year = 2024, day = 16, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part2::getShortestPath);
    }

    private static int getShortestPath(Set<Position> walls, Position end, Node start) {
        List<Node> solutions = new LinkedList<>();

        Set<Node> visited = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();
        unvisited.add(start);

        while (!Objects.equals(unvisited.element().getPosition(), end)) {
            Node current = unvisited.remove();
            visited.add(current);

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (!walls.contains(position) && isNotVisited(visited, position, direction)) {
                    unvisited.add(fromNode(current, position, direction));
                }
            }
        }

        int shortestPath = unvisited.element().getLength();
        while (unvisited.element().getLength() == shortestPath) {
            solutions.add(unvisited.remove());
        }

        return (int) solutions.stream()
                .map(Node::getPath)
                .flatMap(List::stream)
                .distinct()
                .count();
    }

    private static boolean isNotVisited(Set<Node> visited, Position position, Direction direction) {
        return !visited.contains(initialNode(position, direction));
    }
}