package site.ayrilea.advent.solution.year2024.day21;

import java.util.Map;

class DirectionalKeypad extends AbstractKeypad {

    @Override
    Map<String, Position> buttons() {
        return Map.of(
                "^", new Position(0, 1),
                "A", new Position(0, 2),
                "<", new Position(1, 0),
                "v", new Position(1, 1),
                ">", new Position(1, 2)
        );
    }

    @Override
    Position initialPosition() {
        return buttons().get("A");
    }
}
