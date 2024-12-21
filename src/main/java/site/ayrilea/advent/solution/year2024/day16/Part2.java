package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day16.Direction.*;
import static site.ayrilea.advent.solution.year2024.day16.Direction.WEST;

@SolutionMetadata(year = 2024, day = 16, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part2::getShortestPath);
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
            Direction direction = current.getDirection();

            Position up = new Position(row - 1, column);
            if (!walls.contains(up) && isNotVisited(visited, up, NORTH)) {
                unvisited.add(new Node(up, NORTH, current.getLength() + calculateTurningCost(direction, NORTH) + 1));
            }

            Position down = new Position(row + 1, column);
            if (!walls.contains(down) && isNotVisited(visited, down, SOUTH)) {
                unvisited.add(new Node(down, SOUTH, current.getLength() + calculateTurningCost(direction, SOUTH) + 1));
            }

            Position left = new Position(row, column - 1);
            if (!walls.contains(left) && isNotVisited(visited, left, EAST)) {
                unvisited.add(new Node(left, EAST, current.getLength() + calculateTurningCost(direction, EAST) + 1));
            }

            Position right = new Position(row, column + 1);
            if (!walls.contains(right) && isNotVisited(visited, right, WEST)) {
                unvisited.add(new Node(right, WEST, current.getLength() + calculateTurningCost(direction, WEST) + 1));
            }
        }
        return current.getLength();
    }

    private static int calculateTurningCost(Direction current, Direction next) {
        if (current == next) {
            return 0;
        }
        return 1000;
    }

    private static boolean isNotVisited(Set<Node> visited, Position position, Direction direction) {
        return !visited.contains(new Node(position, direction));
    }
}