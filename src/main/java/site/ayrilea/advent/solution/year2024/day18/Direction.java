package site.ayrilea.advent.solution.year2024.day18;

enum Direction {

    DOWN(new Position(1, 0)),
    LEFT(new Position(0, -1)),
    RIGHT(new Position(0, 1)),
    UP(new Position(-1, 0));

    private final Position modifier;

    Direction(Position modifier) {
        this.modifier = modifier;
    }

    Position move(Position current) {
        return new Position(current.row() + modifier.row(), current.column() + modifier.column());
    }
}
