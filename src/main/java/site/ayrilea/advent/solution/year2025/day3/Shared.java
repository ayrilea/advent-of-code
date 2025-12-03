package site.ayrilea.advent.solution.year2025.day3;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Shared {

    static Long solve(Input input, int numberOfBatteriesEnabled) {
        return input.stream()
                .map(Shared::parseInputLine)
                .map(bank -> maxJoltage(bank, numberOfBatteriesEnabled))
                .mapToLong(l -> l)
                .sum();
    }

    private static List<Integer> getMaxDigitIndexes(List<Integer> bank, int startIndex, int endSkipAmount) {
        List<Integer> maxDigitIndexes = new ArrayList<>();
        int maxDigit = -1;

        for (int i = startIndex; i < bank.size() - endSkipAmount; i++) {
            int battery = bank.get(i);
            if (battery == maxDigit) {
                maxDigitIndexes.add(i);
            } else if (battery > maxDigit) {
                maxDigit = battery;
                maxDigitIndexes = new ArrayList<>();
                maxDigitIndexes.add(i);
            }
        }

        return maxDigitIndexes;
    }

    private static long maxJoltage(List<Integer> bank, int numberOfBatteriesEnabled) {
        return maxJoltage(bank, numberOfBatteriesEnabled, 1, List.of(-1), Collections.emptyList());
    }

    private static long maxJoltage(List<Integer> bank, int numberOfBatteriesEnabled, int batteryNumber,
                                   List<Integer> startIndexes, List<String> enabledBank) {
        if (enabledBank.size() == numberOfBatteriesEnabled) {
            return Long.parseLong(String.join("", enabledBank));
        }

        long max = 0L;
        for (int i = 0; i < startIndexes.size(); i++) {
            List<Integer> nextDigitIndexes = getMaxDigitIndexes(bank, startIndexes.get(i) + 1,
                    numberOfBatteriesEnabled - batteryNumber);

            int nextDigit = bank.get(nextDigitIndexes.getFirst());
            List<String> nextEnabledBank = new ArrayList<>(enabledBank);
            nextEnabledBank.add(String.valueOf(nextDigit));

            max = nextDigitIndexes.stream()
                    .map(nextDigitIndex -> maxJoltage(bank, numberOfBatteriesEnabled, batteryNumber + 1,
                            List.of(nextDigitIndex), nextEnabledBank))
                    .mapToLong(l -> l)
                    .max()
                    .orElseThrow();
        }
        return max;
    }

    private static List<Integer> parseInputLine(String line) {
        return Arrays.stream(line.split(""))
                .map(Integer::parseInt)
                .toList();
    }
}
