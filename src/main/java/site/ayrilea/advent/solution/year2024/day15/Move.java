package site.ayrilea.advent.solution.year2024.day15;

import java.util.Objects;

enum Move {

    DOWN("v"),
    LEFT("<"),
    RIGHT(">"),
    UP("^");

    private final String label;

    Move(String label) {
        this.label = label;
    }

    static Move fromLabel(String label) {
        for (Move move : Move.values()) {
            if (Objects.equals(move.label, label)) {
                return move;
            }
        }
        throw new IllegalArgumentException("Invalid label: " + label);
    }
}
