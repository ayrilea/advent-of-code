package site.ayrilea.advent.solution.year2025.day3;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Shared {

    static Long solve(Input input, int numberOfBatteriesEnabled) {
        return input.stream()
                .map(Shared::parseInputLine)
                .map(Shared::maxJoltage)
                .mapToLong(l -> l)
                .sum();
    }

    private static long maxJoltage(List<Integer> bank) {
        List<Long> possibleMaxJoltages = new ArrayList<>();

        List<Integer> firstDigitIndexes = new ArrayList<>();
        int firstDigit = -1;

        for (int i = 0; i < bank.size() - 1; i++) {
            int battery = bank.get(i);
            if (battery == firstDigit) {
                firstDigitIndexes.add(i);
            } else if (battery > firstDigit) {
                firstDigit = battery;
                firstDigitIndexes = new ArrayList<>();
                firstDigitIndexes.add(i);
            }
        }

        System.out.println("Largest digit (not counting final digit)");
        System.out.println("Digit=[" + firstDigit + "]");
        System.out.println("Indexes=[" + String.join(", ", firstDigitIndexes.stream().map(String::valueOf).toList()) + "]");
        for (int i = 0; i < firstDigitIndexes.size(); i++) {
            int secondDigit = -1;
            for (int j = firstDigitIndexes.get(i) + 1; j < bank.size(); j++) {
                int battery = bank.get(j);
                if (battery > secondDigit) {
                    secondDigit = battery;
                }
            }
            System.out.println("Possible second digit: " + secondDigit);
            possibleMaxJoltages.add(Long.parseLong(String.valueOf(firstDigit) + String.valueOf(secondDigit)));
        }
        System.out.println();

        return possibleMaxJoltages.stream()
                .mapToLong(l -> l)
                .max()
                .orElseThrow();
    }

    private static List<Integer> parseInputLine(String line) {
        return Arrays.stream(line.split(""))
                .map(Integer::parseInt)
                .toList();
    }
}
