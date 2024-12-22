package site.ayrilea.advent.solution.year2024.day20;

import site.ayrilea.advent.input.Input;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day20.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day20.Node.initialNode;

class Solver {

    private final Position end;
    private final Position max;
    private final Position start;
    private final Set<Position> walls;
    private final boolean[][] wallLookup;

    private Solver(Position end, Position max, Position start, Set<Position> walls) {
        this.end = end;
        this.max = max;
        this.start = start;
        this.walls = walls;

        wallLookup = parseWalls(walls, max);
    }

    static Solver parseInput(Input input) {
        Set<Position> walls = new HashSet<>();
        Position start = null;
        Position end = null;

        List<String> lines = input.list();
        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int column = 0; column < line.length(); column++) {
                char current = line.charAt(column);
                if ('#' == current) {
                    walls.add(new Position(row, column));
                } else if ('S' == current) {
                    start = new Position(row, column);
                } else if ('E' == current) {
                    end = new Position(row, column);
                }
            }
        }

        if (end == null) {
            throw new IllegalArgumentException("Invalid input: missing end position");
        }
        if (start == null) {
            throw new IllegalArgumentException("Invalid input: missing start position");
        }

        return new Solver(end, new Position(lines.size() - 1, lines.getFirst().length() - 1), start, Set.copyOf(walls));
    }

    int getShortestPath() {
        Set<Position> visited = new HashSet<>();
        Set<Position> unvisitedUnique = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();

        unvisitedUnique.add(start);
        unvisited.add(initialNode(start));

        while (!unvisited.isEmpty()) {
            Node current = unvisited.remove();
            if (Objects.equals(current.getPosition(), end)) {
                return current.getLength();
            }
            visited.add(current.getPosition());

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (isInBounds(position, max) &&
                        !wallLookup[position.row()][position.column()] &&
                        !visited.contains(position) &&
                        !unvisitedUnique.contains(position)) {
                    unvisited.add(fromNode(current, position));
                    unvisitedUnique.add(position);
                }
            }
        }

        return -1;
    }

    List<Integer> getTimesWithCheat(int cheatStep) {
        Set<Position> visited = new HashSet<>();
        Set<Position> unvisitedUnique = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();

        unvisitedUnique.add(start);
        unvisited.add(initialNode(start));

        int step = 0;
        while (!unvisited.isEmpty()) {
            Node current = unvisited.remove();
            if (Objects.equals(current.getPosition(), end)) {
                return List.of(current.getLength());
            }
            visited.add(current.getPosition());

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (isInBounds(position, max) &&
                        !wallLookup[position.row()][position.column()] &&
                        !visited.contains(position) &&
                        !unvisitedUnique.contains(position)) {
                    unvisited.add(fromNode(current, position));
                    unvisitedUnique.add(position);
                }
            }

            //..2..
            //.212.
            //21X12
            //.212.
            //..2..
            if (step == cheatStep) {
                Set<Position> cheatPositions = new HashSet<>();

                Position position = current.getPosition();
                int row = position.row();
                int column = position.column();
                cheatPositions.add(new Position(row - 2, column));
                cheatPositions.add(new Position(row - 1, column + 1));
                cheatPositions.add(new Position(row, column + 2));
                cheatPositions.add(new Position(row + 1, column + 1));
                cheatPositions.add(new Position(row + 2, column));
                cheatPositions.add(new Position(row + 1, column - 1));
                cheatPositions.add(new Position(row, column - 2));
                cheatPositions.add(new Position(row - 1, column - 1));

                List<Integer> times = new LinkedList<>();
                for (Position cheatPosition : cheatPositions) {
                    if (isInBounds(cheatPosition, max) &&
                            !wallLookup[cheatPosition.row()][cheatPosition.column()] &&
                            !visited.contains(cheatPosition) &&
                            !unvisitedUnique.contains(cheatPosition)) {
                        int time = getShortestPath(wallLookup, max, new HashSet<>(visited),
                                new HashSet<>(unvisitedUnique), end, fromNode(current, cheatPosition, 2));
                        if (time != -1 ) {
                            times.add(time);
                        }
                    }
                }
                return times;
            }
            step++;
        }

        return Collections.emptyList();
    }

    private static int getShortestPath(boolean[][] wallLookup, Position max, Set<Position> visited,
                                       Set<Position> unvisitedUnique, Position end, Node start) {
        Queue<Node> unvisited = new PriorityQueue<>();

        unvisitedUnique.add(start.getPosition());
        unvisited.add(start);

        while (!unvisited.isEmpty()) {
            Node current = unvisited.remove();
            if (Objects.equals(current.getPosition(), end)) {
                return current.getLength();
            }
            visited.add(current.getPosition());

            for (Direction direction : Direction.values()) {
                Position position = direction.move(current.getPosition());
                if (isInBounds(position, max) &&
                        !wallLookup[position.row()][position.column()] &&
                        !visited.contains(position) &&
                        !unvisitedUnique.contains(position)) {
                    unvisited.add(fromNode(current, position));
                    unvisitedUnique.add(position);
                }
            }
        }

        return -1;
    }

    private static boolean isInBounds(Position position, Position max) {
        return position.row() >= 0 && position.row() <= max.row() &&
                position.column() >= 0 && position.column() <= max.column();
    }

    private static boolean[][] parseWalls(Set<Position> walls, Position max) {
        boolean[][] wallLookup = new boolean[max.row() + 1][max.column() + 1];
        for (int row = 0; row <= wallLookup.length; row++) {
            for (int column = 0; column <= wallLookup[0].length; column++) {
                if (walls.contains(new Position(row, column))) {
                    wallLookup[row][column] = true;
                }
            }
        }
        return wallLookup;
    }
}
