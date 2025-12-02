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
        return Integrator.of((state, rotation, _) -> {
            int value = state.get(0);
            int count = state.get(1);

            //Apply the net rotation to the current value
            int originalValue = value;
            value += rotation % 100;

            //Count actually stopping at 0
            if (value % 100 == 0) {
                count++;
            }

            //Count passes of dial 0
            if (includePasses) {
                //Count any full rotations (which each pass dial 0 once)
                count += Math.abs(rotation / 100); //Rotation is negative if moving Left

                //For partial rotations, it's not a pass of dial 0 if starting or ending at 0
                if (value % 100 != 0 && originalValue % 100 != 0) {
                    //Count passes of _dial_ 0 (other than past value == 0, since there's no "negative 0")
                    if (value / 100 != originalValue / 100) {
                        count++;
                    }

                    //Extra check for passing value == 0 (where both value / 100 and originalValue / 100 are 0)
                    //It is a pass of dial 0 when the value moves from positive to negative or vice versa
                    if (haveOppositeSigns(originalValue, value)) {
                        count++;
                    }
                }
            }

            state.set(0, value);
            state.set(1, count);
            return true;
        });
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

    private static boolean haveOppositeSigns(int a, int b) {
        return (a ^ b) < 0;
    }
}
