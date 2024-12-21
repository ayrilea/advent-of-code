package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;

import java.util.*;
import java.util.function.Function;

import static site.ayrilea.advent.solution.year2024.day16.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day16.Node.initialNode;

class Shared {

    private static final Direction START_DIRECTION = Direction.EAST;

    static int solve(Input input, Function<List<Node>, Integer> solver) {
        return solver.apply(parseInputAndApply(input, Shared::findShortestPaths));
    }

    private static List<Node> findShortestPaths(Set<Position> walls, Position end, Node start) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();
        unvisited.add(start);

        while (!Objects.equals(unvisited.element().getPosition(), end)) {
            Node current = unvisited.remove();
            visited.add(current);

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (!walls.contains(position) && !visited.contains(initialNode(position, direction))) {
                    unvisited.add(fromNode(current, position, direction));
                }
            }
        }

        int shortestPath = unvisited.element().getLength();
        List<Node> solutions = new LinkedList<>();
        while (unvisited.element().getLength() == shortestPath) {
            solutions.add(unvisited.remove());
        }

        return solutions;
    }

    private static List<Node> parseInputAndApply(Input input, SolutionFinder findSolutions) {
        Position end = new Position(0, 0);
        Position start = new Position(0, 0);
        Set<Position> walls = new HashSet<>();

        List<String> lines = input.list();
        for (int row = 0; row < lines.size(); row++) {
            String[] parts = lines.get(row).split("");
            for (int column = 0; column < parts.length; column++) {
                char current = lines.get(row).charAt(column);
                if (current == '#') {
                    walls.add(new Position(row, column));
                } else if (current == 'S') {
                    start = new Position(row, column);
                } else if (current == 'E') {
                    end = new Position(row, column);
                }
            }
        }

        return findSolutions.apply(walls, end, initialNode(start, START_DIRECTION));
    }

    @FunctionalInterface
    private interface SolutionFinder {

        List<Node> apply(Set<Position> walls, Position end, Node start);
    }
}
