package site.ayrilea.advent.solution.year2025.day7;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.Objects;

class Diagram {

    private final Space[][] diagram;

    private int splitCount;

    private Diagram(Space[][] diagram) {
        this.diagram = diagram;
    }

    int getSplitCount() {
        return splitCount;
    }

    void simulate() {
        for (int row = 0; row < diagram.length; row++) {
            for (int column = 0; column < diagram[row].length; column++) {
                diagram[row][column] = switch (diagram[row][column]) {
                    case BEAM, START -> Space.BEAM;
                    case EMPTY -> {
                        if (row == 0) {
                            yield Space.EMPTY;
                        }
                        if (diagram[row - 1][column] == Space.SPLITTER) {
                            yield Space.EMPTY;
                        }
                        //If under an empty, stay empty. If under a beam, stay beam.
                        yield diagram[row - 1][column];
                    }
                    case SPLITTER -> {
                        if (row != 0 && diagram[row - 1][column] == Space.BEAM) {
                            if (column > 0) {
                                diagram[row][column - 1] = Space.BEAM;
                            }
                            if (column < diagram[row].length - 1) {
                                diagram[row][column + 1] = Space.BEAM;
                            }
                            splitCount++;
                        }
                        yield Space.SPLITTER;
                    }
                };
            }
        }
    }

    static Diagram fromInput(Input input) {
        return new Diagram(input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Space::fromLabel)
                        .toArray(Space[]::new))
                .toArray(Space[][]::new));
    }

    private enum Space {

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
}
