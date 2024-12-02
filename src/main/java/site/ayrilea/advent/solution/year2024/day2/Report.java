package site.ayrilea.advent.solution.year2024.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Gatherers.*;

public record Report(List<Integer> levels) {

    public boolean isSafe() {
        return isSafe(levels);
    }

    public boolean isSafeWithTolerance() {
        if (isSafe()) {
            return true;
        }
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> temporaryLevels = new ArrayList<>(levels);
            temporaryLevels.remove(i);
            if (isSafe(temporaryLevels)) {
                return true;
            }
        }
        return false;
    }

    public static Report fromInput(String line) {
        return new Report(Arrays.stream(line.split(" "))
                .map(Integer::valueOf)
                .toList());
    }

    private static Order getOrder(List<Integer> list) {
        return list.get(0) < list.get(1) ? Order.ASCENDING : Order.DESCENDING;
    }

    private static boolean isGradual(List<Integer> values) {
        int difference = Math.abs(values.get(0) - values.get(1));
        return difference >= 1 && difference <= 3;
    }

    private static boolean isOrdered(Order order, List<Integer> values) {
        return order == Order.ASCENDING ? values.get(0) < values.get(1) :
                values.get(0) > values.get(1);
    }

    private static boolean isSafe(List<Integer> list) {
        Order order = getOrder(list);
        return list.stream()
                .gather(windowSliding(2))
                .filter(values -> isOrdered(order, values))
                .filter(Report::isGradual)
                .count() == list.size() - 1;
    }

    private enum Order {
        ASCENDING,
        DESCENDING;
    }
}
