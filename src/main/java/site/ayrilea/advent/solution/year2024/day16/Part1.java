package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day16.Direction.*;

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

            int column = current.getPosition().column();
            int row = current.getPosition().row();

            Position up = new Position(row - 1, column);
            if (!walls.contains(up) && isNotVisited(visited, up, NORTH)) {
                unvisited.add(new Node.Builder(up, NORTH)
                        .lengthFrom(current)
                        .build());
            }

            Position down = new Position(row + 1, column);
            if (!walls.contains(down) && isNotVisited(visited, down, SOUTH)) {
                unvisited.add(new Node.Builder(down, SOUTH)
                        .lengthFrom(current)
                        .build());
            }

            Position left = new Position(row, column - 1);
            if (!walls.contains(left) && isNotVisited(visited, left, EAST)) {
                unvisited.add(new Node.Builder(left, EAST)
                        .lengthFrom(current)
                        .build());
            }

            Position right = new Position(row, column + 1);
            if (!walls.contains(right) && isNotVisited(visited, right, WEST)) {
                unvisited.add(new Node.Builder(right, WEST)
                        .lengthFrom(current)
                        .build());
            }
        }
        return current.getLength();
    }

    private static boolean isNotVisited(Set<Node> visited, Position position, Direction direction) {
        return !visited.contains(new Node(position, direction));
    }
}
