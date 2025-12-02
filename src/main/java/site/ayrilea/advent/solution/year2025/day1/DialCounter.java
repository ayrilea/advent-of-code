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
            //Push the count downstream
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

                    //netRotations is in the range [-99,99], and value is in the range [0,99]
                    //This will leave value in the range [-99,198]
                    value += netRotation;

                    //First, count actually stopping at 0
                    //This includes 100 (since the netRotation allows for +100 exclusive either way around)
                    if (value == 0 || value == 100) {
                        value = 0; //Set proper position here
                        count++;
                    }

                    //Next, adjust for if we're outside proper value range [0,99]
                    //This will require either a single +100, a single -100, or nothing
                    if (value > 99) {
                        value -= 100;
                        //We moved from [0,99] to [100,198] in the net rotation, which passed 0
                        if (includePasses) {
                            count++;
                        }
                    } else if (value < 0) {
                        value += 100;
                        //We moved from [0,99] to [-99,-1] in the net rotation
                        if (originalValue != 0) {
                            //This handles if we _started_ at zero (which is not another pass)
                            if (includePasses) {
                                count++;
                            }
                        }
                    }

                    //Count the full rotations as passing 0
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
