package site.ayrilea.advent.solution.year2025.day7;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Diagram {

    private final Space[][] diagram;
    private final Map<Coordinate, Long> timelinesFromCoordinate;

    private Diagram(Space[][] diagram) {
        this.diagram = diagram;
        this.timelinesFromCoordinate = new HashMap<>();
    }

    long getNumberOfTimelines() {
        return getNumberOfTimelines(0, getStartColumn());
    }

    int getSplitCountForSimulatedBeam() {
        //Not strictly required, but nice to not mutate the input state so the same Diagram could in re-used for Part1
        //Part2 if needed.
        Space[][] diagram = Arrays.stream(this.diagram)
                .map(Space[]::clone)
                .toArray(Space[][]::new);
        int splitCount = 0;

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

        return splitCount;
    }

    private long getNumberOfTimelines(int row, int column) {
        if (row == diagram.length - 1) {
            return 1;
        }

        //Cache count from the current position, since it doesn't change from here to the base case and can be used for
        //other timelines. This is required to make this logic run fast enough on the real puzzle input.
        Coordinate coordinate = new Coordinate(row, column);
        if (!timelinesFromCoordinate.containsKey(coordinate)) {
            long count;
            if (diagram[row][column] != Space.SPLITTER) {
                //If we're not at a splitter, the count of this position is the same as the count of one position down.
                count = getNumberOfTimelines(row + 1, column);
            } else {
                //If we are at a splitter, the count is the sum of the left branch and right branch.
                count = getNumberOfTimelines(row + 1, column - 1) +
                        getNumberOfTimelines(row + 1, column + 1);
            }
            timelinesFromCoordinate.put(coordinate, count);
        }

        return timelinesFromCoordinate.get(coordinate);
    }

    private int getStartColumn() {
        return IntStream.range(0, diagram[0].length)
                .filter(index -> diagram[0][index] == Space.START)
                .findFirst()
                .orElseThrow();
    }

    static Diagram fromInput(Input input) {
        return new Diagram(input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(Space::fromLabel)
                        .toArray(Space[]::new))
                .toArray(Space[][]::new));
    }
}
