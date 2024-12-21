package site.ayrilea.advent.solution.year2024.day16;

public enum Direction {

    EAST(new Position(0, 1)),
    NORTH(new Position(-1, 0)),
    SOUTH(new Position(1, 0)),
    WEST(new Position(0, -1));

    private final Position modifier;

    Direction(Position modifier) {
        this.modifier = modifier;
    }

    Position move(Position current) {
        return new Position(current.row() + modifier.row(), current.column() + modifier.column());
    }
}
