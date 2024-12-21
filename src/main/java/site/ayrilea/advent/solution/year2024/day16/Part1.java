package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day16.Direction.*;

@SolutionMetadata(year = 2024, day = 16, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        Set<Position> walls = new HashSet<>();
        Position start = new Position(0, 0);
        Position end = new Position(0, 0);
        Direction direction = EAST;

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

        return getShortestPath(walls, start, direction, end);
    }

    private static long getShortestPath(Set<Position> walls, Position start, Direction startDirection, Position end) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();
        unvisited.add(new Node(start, startDirection));

        Node current = unvisited.element();
        while (!Objects.equals(current.position, end)) {
            current = unvisited.remove();
            visited.add(current);

            int column = current.position.column();
            int row = current.position.row();
            Direction direction = current.direction;

            Position up = new Position(row - 1, column);
            if (!walls.contains(up) && !isVisited(visited, up, NORTH)) {
                unvisited.add(new Node(up, NORTH, current.length + (int) calculateTurningCost(direction, NORTH) + 1));
            }

            Position down = new Position(row + 1, column);
            if (!walls.contains(down) && !isVisited(visited, down, SOUTH)) {
                unvisited.add(new Node(down, SOUTH, current.length + (int) calculateTurningCost(direction, SOUTH) + 1));
            }

            Position left = new Position(row, column - 1);
            if (!walls.contains(left) && !isVisited(visited, left, EAST)) {
                unvisited.add(new Node(left, EAST, current.length + (int) calculateTurningCost(direction, EAST) + 1));
            }

            Position right = new Position(row, column + 1);
            if (!walls.contains(right) && !isVisited(visited, right, WEST)) {
                unvisited.add(new Node(right, WEST, current.length + (int) calculateTurningCost(direction, WEST) + 1));
            }
        }
        return current.length;
    }

    private static long calculateTurningCost(Direction current, Direction next) {
        if (current == next) {
            return 0L;
        }
        return 1000L;
    }

    private static boolean isVisited(Set<Node> visited, Position position, Direction direction) {
        return visited.contains(new Node(position, direction));
    }

    private static class Node implements Comparable<Node> {

        private final Direction direction;
        private final Position position;

        private int length;

        private Node(Position position, Direction direction) {
            this.direction = direction;
            this.position = position;

            length = 0;
        }

        private Node(Position position, Direction direction, int length) {
            this.direction = direction;
            this.length = length;
            this.position = position;
        }

        @Override
        public int compareTo(Node o) {
            return length - o.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, position);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Node n) {
                return Objects.equals(direction, n.direction) && Objects.equals(position, n.position);
            }
            return false;
        }
    }
}
