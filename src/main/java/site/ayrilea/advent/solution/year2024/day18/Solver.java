package site.ayrilea.advent.solution.year2024.day18;

import site.ayrilea.advent.input.Input;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day18.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day18.Node.initialNode;

class Solver {

    private final List<Position> allBytes;
    private final int bytesReference;
    private final Position end;
    private final Position start;

    Solver(Input input) {
        List<String> lines = input.list();
        bytesReference = Integer.parseInt(lines.getFirst());
        allBytes = parseInput(lines.subList(1, lines.size()));

        end = getEndPosition(allBytes);
        start = new Position(0, 0);
    }

    Position getByte(int index) {
        return allBytes.get(index);
    }

    int getBytesReference() {
        return bytesReference;
    }

    int getTotalBytes() {
        return allBytes.size();
    }

    int getShortestPath(int numBytes) {
        return getShortestPath(allBytes, numBytes, end, start);
    }

    private static Position getEndPosition(List<Position> bytes) {
        int maxRow = bytes.stream()
                .map(Position::row)
                .mapToInt(Integer::valueOf)
                .max()
                .orElse(0);
        int maxColumn = bytes.stream()
                .map(Position::column)
                .mapToInt(Integer::valueOf)
                .max()
                .orElse(0);
        return new Position(maxRow, maxColumn);
    }

    private static int getShortestPath(List<Position> allBytes, int numBytes, Position end, Position start) {
        Set<Position> bytes = new HashSet<>(allBytes.subList(0, numBytes));
        Set<Position> visited = new HashSet<>();
        Set<Position> unvisitedUnique = new HashSet<>();
        Queue<Node> unvisited = new LinkedList<>();

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
                if (isInBounds(position, end) &&
                        !bytes.contains(position) &&
                        !visited.contains(position) &&
                        !unvisitedUnique.contains(position)) {
                    unvisited.add(fromNode(current, position));
                    unvisitedUnique.add(position);
                }
            }
        }

        return -1;
    }

    private static boolean isInBounds(Position position, Position end) {
        return position.row() >= 0 && position.row() <= end.row() &&
                position.column() >= 0 && position.column() <= end.column();
    }

    private static List<Position> parseInput(List<String> lines) {
        return lines.stream()
                .map(Position::fromLine)
                .toList();
    }
}
