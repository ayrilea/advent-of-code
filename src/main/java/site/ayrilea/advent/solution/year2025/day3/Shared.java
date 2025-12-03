package site.ayrilea.advent.solution.year2025.day3;

import site.ayrilea.advent.input.Input;

import java.awt.*;
import java.util.Arrays;

class Shared {

    static Long solve(Input input, int numberOfBatteriesToEnable) {
        return input.stream()
                .map(Shared::parseInputLine)
                .map(bank -> bank.maxJoltage(numberOfBatteriesToEnable))
                .mapToLong(l -> l)
                .sum();
    }

    private static Bank parseInputLine(String line) {
        return new Bank(Arrays.stream(line.split(""))
                .map(Integer::parseInt)
                .toList());
    }
}
