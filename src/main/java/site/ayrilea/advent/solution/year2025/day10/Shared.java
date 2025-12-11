package site.ayrilea.advent.solution.year2025.day10;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Shared {

    static int solve(Input input, BiFunction<Machine, List<Integer>, Boolean> applyPresses) {
        return input.stream()
                .map(Machine::fromInput)
                .map(machine -> {
                    System.out.println("new machine");
                    return calculateFewestButtonPressesNeeded(machine, applyPresses);
                })
                .mapToInt(i -> i)
                .sum();
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int nButtons, int remaining,
                                  int start) {
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

    private static int calculateFewestButtonPressesNeeded(Machine machine,
                                                          BiFunction<Machine, List<Integer>, Boolean> applyPresses) {
        int buttonPresses = 1;
        while (!canSolve(machine, buttonPresses, applyPresses)) {
            buttonPresses++;
        }

        return buttonPresses;
    }

    private static boolean canSolve(Machine machine, int buttonPresses,
                                    BiFunction<Machine, List<Integer>, Boolean> applyPresses) {
        List<List<Integer>> buttonCombinations = getButtonCombinations(machine.buttons().size(), buttonPresses);

        for (var buttonCombination :buttonCombinations) {
            if (applyPresses.apply(machine, buttonCombination)) {
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
