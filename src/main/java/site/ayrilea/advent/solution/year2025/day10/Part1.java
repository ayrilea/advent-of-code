package site.ayrilea.advent.solution.year2025.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SolutionMetadata(year = 2025, day = 10, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return input.stream()
                .map(Machine::fromInput)
                .map(Part1::calculateFewestButtonPressesNeeded)
                .mapToInt(i -> i)
                .sum();
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

    private static void backtrack(List<List<Integer>> result, List<Integer> current,
                                  int nButtons, int remaining, int start) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < nButtons; i++) {
            current.add(i);
            backtrack(result, current, nButtons, remaining - 1, i);
            current.removeLast();
        }
    }

    private static int calculateFewestButtonPressesNeeded(Machine machine) {
        int buttonPresses = 1;
        while (!canSolve(machine, buttonPresses)) {
            buttonPresses++;
        }

        return buttonPresses;
    }

    private static boolean canSolve(Machine machine, int buttonPresses) {
        List<List<Integer>> buttonCombinations = getButtonCombinations(machine.buttons().size(), buttonPresses);

        for (var buttonCombination :buttonCombinations) {
            if (applyPresses(machine, buttonCombination)) {
                return true;
            }
        }
        return false;
    }

    private static List<List<Integer>> getButtonCombinations(int nButtons, int nPresses) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nButtons, nPresses, 0);
        return result;
    }
}