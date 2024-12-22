package site.ayrilea.advent.solution.year2024.day18;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static site.ayrilea.advent.solution.year2024.day18.Node.fromNode;
import static site.ayrilea.advent.solution.year2024.day18.Node.initialNode;
import static site.ayrilea.advent.solution.year2024.day18.Shared.parseInput;

@SolutionMetadata(year = 2024, day = 18, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Set<Position> bytes = parseInput(input);
        Position start = new Position(0, 0);
        Position end = getEndPosition(bytes);
        return getShortestPath(bytes, end, start);
    }

    private static Position getEndPosition(Set<Position> bytes) {
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

    private static int getShortestPath(Set<Position> bytes, Position end, Position start) {
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

        return 0;
    }

    private static boolean isInBounds(Position position, Position end) {
        return position.row() >= 0 && position.row() <= end.row() &&
                position.column() >= 0 && position.column() <= end.column();
    }
}
