package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

@SolutionMetadata(year = 2024, day = 16, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part1::getShortestPath);
    }

    private static int getShortestPath(Set<Position> walls, Position end, Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();
        unvisited.add(start);

        Node current = unvisited.element();
        while (!Objects.equals(current.getPosition(), end)) {
            current = unvisited.remove();
            visited.add(current);

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (!walls.contains(position) && isNotVisited(visited, position, direction)) {
                    unvisited.add(new Node.Builder(position, direction)
                            .lengthFrom(current)
                            .build());
                }
            }
        }
        return current.getLength();
    }

    private static boolean isNotVisited(Set<Node> visited, Position position, Direction direction) {
        return !visited.contains(new Node(position, direction));
    }
}
