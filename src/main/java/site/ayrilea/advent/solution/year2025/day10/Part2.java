package site.ayrilea.advent.solution.year2025.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SolutionMetadata(year = 2025, day = 10, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part2::applyPresses);
    }

    private static boolean applyPresses(Machine machine, List<Integer> buttonPresses) {
        int[] state = new int[machine.desiredState().length];

        buttonPresses.forEach(buttonIndex -> {
            Boolean[] button = machine.buttons().get(buttonIndex);
            for (int i = 0; i < button.length; i++) {
                if (button[i]) {
                    state[i] += 1;
                }
            }
        });

        return Arrays.equals(state, machine.desiredJoltage());
    }
}