package site.ayrilea.advent.solution.year2025.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

@SolutionMetadata(year = 2025, day = 10, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part1::applyPresses);
    }

    private static boolean applyPresses(Machine machine, List<Integer> buttonPresses) {
        Boolean[] state = new Boolean[machine.desiredState().length];
        IntStream.range(0, state.length).forEach(i -> state[i] = false);
        buttonPresses.forEach(buttonIndex -> {
            Boolean[] button = machine.buttons().get(buttonIndex);
            for (int i = 0; i < state.length; i++) {
                state[i] = state[i] ^ button[i];
            }
        });

        return Arrays.equals(state, machine.desiredState());
    }
}