package site.ayrilea.advent.solution.year2025.day7;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Diagram {

    private final Space[][] diagram;
    private final Map<Coordinate, Long> timelinesFromCoordinate;

    private int splitCount;

    private Diagram(Space[][] diagram) {
        this.diagram = diagram;
        this.timelinesFromCoordinate = new HashMap<>();
    }

    int getSplitCount() {
        return splitCount;
    }

    long getNumberOfTimelines() {
        Space[] firstRow = diagram[0];
        int startColumn = -1;
        for (int i = 0; i < firstRow.length; i++) {
            if (diagram[0][i] == Space.START) {
                startColumn = i;
            }
        }
        if (startColumn == -1) {
            throw new IllegalStateException("No starting space found.");
        }
        return getNumberOfTimelines(0, startColumn);
    }

    void simulateBeam() {
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

    private long getNumberOfTimelines(int row, int column) {
        if (row == diagram.length - 1) {
            return 1;
        }

        Coordinate coordinate = new Coordinate(row, column);
        if (timelinesFromCoordinate.containsKey(coordinate)) {
            return timelinesFromCoordinate.get(coordinate);
        }

        Space current = diagram[row][column];
        if (current != Space.SPLITTER) {
            return getNumberOfTimelines(row + 1, column);
        }
        long count = getNumberOfTimelines(row + 1, column - 1) +
                getNumberOfTimelines(row + 1, column + 1);
        timelinesFromCoordinate.put(coordinate, count);
        return count;
    }

    static Diagram fromInput(Input input) {
        return new Diagram(input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Space::fromLabel)
                        .toArray(Space[]::new))
                .toArray(Space[][]::new));
    }

    private record Coordinate(int row, int column) {

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Coordinate(int otherRow, int otherColumn)) {
                return row == otherRow && column == otherColumn;
            }
            return false;
        }

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
