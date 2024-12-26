package site.ayrilea.advent.solution.year2024.day21;

import java.util.Map;

import static java.util.Map.entry;

class NumericKeypad extends AbstractKeypad {

    @Override
    Map<String, Position> buttons() {
        return Map.ofEntries(
                entry("7", new Position(0, 0)),
                entry("8", new Position(0, 1)),
                entry("9", new Position(0, 2)),
                entry("4", new Position(1, 0)),
                entry("5", new Position(1, 1)),
                entry("6", new Position(1, 2)),
                entry("1", new Position(2, 0)),
                entry("2", new Position(2, 1)),
                entry("3", new Position(2, 2)),
                entry("0", new Position(3, 1)),
                entry("A", new Position(3, 2))
        );
    }

    @Override
    Position initialPosition() {
        return buttons().get("A");
    }
}
