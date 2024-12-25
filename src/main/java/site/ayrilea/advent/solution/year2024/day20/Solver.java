package site.ayrilea.advent.solution.year2024.day20;

import site.ayrilea.advent.input.Input;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day20.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day20.Node.initialNode;

class Solver {

    private final Position end;
    private final Position max;
    private final List<Position> path;
    private final Position start;
    private final boolean[][] wallLookup;

    private Solver(Position end, Position max, Position start, Set<Position> walls) {
        this.end = end;
        this.max = max;
        this.start = start;
        wallLookup = parseWalls(walls, max);

        path = getPath();
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

    Map<Integer, Integer> getCheatsByTimeSave(int maxCheatLength) {
        Map<Integer, Integer> cheatsByTimeSave = new HashMap<>();
        for (int step = 0; step < getSecondsForPath(); step++) {
            getTimeSavesWithCheat(step, maxCheatLength)
                    .forEach(time -> cheatsByTimeSave.compute(time, (_,v) -> v == null ? 1 : v + 1));
        }
        return cheatsByTimeSave;
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

    private Set<Cheat> getCheatPositions(Position position, int maxCheatLength) {
        Set<Cheat> cheatPositions = new HashSet<>();

        for (int cheatLength = 2; cheatLength <= maxCheatLength; cheatLength++) {
            cheatPositions.addAll(getCheatPositionsForLength(position, cheatLength));
        }

        return cheatPositions;
    }

    private Set<Cheat> getCheatPositionsForLength(Position position, int cheatLength) {
        Set<Cheat> cheatPositions = new HashSet<>();
        int row = position.row();
        int column = position.column();

        int rowDiff = -cheatLength;
        int colDiff = 0;
        int rowDirection = -1;
        int colDirection = 1;

        do {
            cheatPositions.add(new Cheat(new Position(row + rowDiff, column + colDiff), cheatLength));
            if (Math.abs(rowDiff) == cheatLength) {
                rowDirection = -rowDirection;
            }
            if (Math.abs(colDiff) == cheatLength) {
                colDirection = -colDirection;
            }
            rowDiff += rowDirection;
            colDiff += colDirection;
        } while (!(rowDiff == -cheatLength && colDiff == 0));
        return cheatPositions;
    }

    private List<Position> getPath() {
        Set<Position> visited = new HashSet<>();
        Set<Position> unvisitedUnique = new HashSet<>();
        Queue<Node> unvisited = new PriorityQueue<>();

        unvisitedUnique.add(start);
        unvisited.add(initialNode(start));

        while (!unvisited.isEmpty()) {
            Node current = unvisited.remove();
            if (Objects.equals(current.getPosition(), end)) {
                return current.getPath();
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

        throw new IllegalStateException("No path available");
    }

    private int getSecondsForPath() {
        return path.size() - 1;
    }

    private List<Integer> getTimeSavesWithCheat(int cheatStep, int maxCheatLength) {
        Node current = new Node(cheatStep, path.get(cheatStep));

        Set<Cheat> cheats = getCheatPositions(current.getPosition(), maxCheatLength);

        List<Integer> times = new LinkedList<>();
        for (Cheat cheat : cheats) {
            Position position = cheat.end();
            if (isInBounds(position, max) &&
                    !wallLookup[position.row()][position.column()]) {
                int index = path.indexOf(position);
                int timeSave = index - cheatStep - cheat.length();
                if (index > cheatStep && timeSave > 0) {
                    times.add(timeSave);
                }
            }
        }
        return times;
    }
}
