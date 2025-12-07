package site.ayrilea.advent.solution.year2025.day7;

import java.util.Objects;

enum Space {

    BEAM("|"),
    EMPTY("."),
    SPLITTER("^"),
    START("S");

    private final String label;

    Space(String label) {
        this.label = label;
    }

    static Space fromLabel(String label) {
        for (Space space : Space.values()) {
            if (Objects.equals(space.label, label)) {
                return space;
            }
        }
        throw new IllegalArgumentException("Invalid label: " + label);
    }
}
