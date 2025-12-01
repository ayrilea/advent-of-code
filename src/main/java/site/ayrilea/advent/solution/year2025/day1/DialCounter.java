package site.ayrilea.advent.solution.year2025.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

record DialCounter(int initial, boolean includePasses) implements Gatherer<Integer, List<Integer>, Integer> {

    @Override
    public BiConsumer<List<Integer>, Downstream<? super Integer>> finisher() {
        return (state, downstream) -> {
            downstream.push(state.get(1));
        };
    }

    @Override
    public Integrator<List<Integer>, Integer, Integer> integrator() {
        return Integrator.of(
                (state, rotation, _) -> {
                    int value = state.get(0);
                    int count = state.get(1);

                    int originalValue = value;
                    int fullRotations = rotation / 100;
                    int netRotation = rotation % 100;

                    value += netRotation;
                    if (value == 100) {
                        value = 0;
                    }
                    if (value > 99) {
                        value -= 100;
                        if (includePasses) {
                            count++;
                        }
                    }
                    if (value < 0) {
                        value += 100;
                        if (originalValue != 0) {
                            if (includePasses) {
                                count++;
                            }
                        }
                    }

                    if (value == 0) {
                        count++;
                    }
                    if (includePasses) {
                        count += Math.abs(fullRotations);
                    }

                    state.set(0, value);
                    state.set(1, count);
                    return true;
                }
        );
    }

    @Override
    public Supplier<List<Integer>> initializer() {
        //state[0] is the current value of the dial
        //state[1] is the count of 0s (respecting includePasses)
        List<Integer> state = new ArrayList<>(2);
        state.add(initial);
        state.add(0);
        return () -> state;
    }
}
