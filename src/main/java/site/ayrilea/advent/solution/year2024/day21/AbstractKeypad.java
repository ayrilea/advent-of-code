package site.ayrilea.advent.solution.year2024.day21;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class AbstractKeypad implements Keypad {

    private Position current;

    AbstractKeypad() {
        current = initialPosition();
    }

    public String inputSequenceFor(String output) {
        return Arrays.stream(output.split(""))
                .map(this::moveAndPress)
                .flatMap(List::stream)
                .collect(Collectors.joining());
    }

    abstract Map<String, Position> buttons();

    abstract Position initialPosition();

    private List<String> moveAndPress(String label) {
        System.out.println(current + " -> " + label);
        Position target = buttons().get(label);
        int columnChange = target.column() - current.column();
        int rowChange = target.row() - current.row();

        if (current.equals(new Position(2, 2)) && target.equals(new Position(0, 0))) {
            current = target;
            return List.of("<", "<", "^", "^", "A");
        }
        if (current.equals(new Position(0, 0)) && target.equals(new Position(2, 2))) {
            current = target;
            return List.of(">", ">", "v", "v", "A");
        }
        if (current.equals(new Position(0, 2)) && target.equals(new Position(2, 0))) {
            current = target;
            return List.of("<", "<", "v", "v", "A");
        }
        if (current.equals(new Position(2, 0)) && target.equals(new Position(0, 2))) {
            current = target;
            return List.of(">", ">", "^", "^", "A");
        }

        List<String> sequence = new LinkedList<>();
        if (columnChange > 0) {
            IntStream.range(0, Math.abs(columnChange)).forEach(_ -> sequence.add(">"));
        }
        if (rowChange > 0) {
            IntStream.range(0, Math.abs(rowChange)).forEach(_ -> sequence.add("v"));
        }
        if (rowChange < 0) {
            IntStream.range(0, Math.abs(rowChange)).forEach(_ -> sequence.add("^"));
        }
        if (columnChange < 0) {
            IntStream.range(0, Math.abs(columnChange)).forEach(_ -> sequence.add("<"));
        }
        sequence.add("A");

        current = target;
        return sequence;
    }
}
