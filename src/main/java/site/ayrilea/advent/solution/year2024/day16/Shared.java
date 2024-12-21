package site.ayrilea.advent.solution.year2024.day16;

import site.ayrilea.advent.input.Input;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static site.ayrilea.advent.solution.year2024.day16.Direction.EAST;
import static site.ayrilea.advent.solution.year2024.day16.Node.initialNode;

class Shared {

    static int solve(Input input, PathFunction pathFunction) {
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

        return pathFunction.apply(walls, end, initialNode(start, direction));
    }
}
