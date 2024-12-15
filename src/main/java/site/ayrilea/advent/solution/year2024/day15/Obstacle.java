package site.ayrilea.advent.solution.year2024.day15;

import java.util.Objects;

enum Obstacle {

    BOULDER("O"),
    NONE("."),
    WALL("#");

    private final String label;

    Obstacle(String label) {
        this.label = label;
    }

    static Obstacle fromLabel(String label) {
        for (Obstacle obstacle : Obstacle.values()) {
            if (Objects.equals(obstacle.label, label)) {
                return obstacle;
            }
        }
        throw new IllegalArgumentException("Invalid label: " + label);
    }
}
