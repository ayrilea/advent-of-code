package site.ayrilea.advent.solution.year2025.day3;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SolutionMetadata(year = 2025, day = 3, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return input.stream()
                .map(Part1::parseInputLine)
                .map(Part1::maxJoltage)
                .mapToInt(i -> i)
                .sum();
    }

    private static int maxJoltage(List<Integer> bank) {
        List<Integer> possibleMaxJoltages = new ArrayList<>();

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
            possibleMaxJoltages.add(Integer.parseInt(String.valueOf(firstDigit) + String.valueOf(secondDigit)));
        }
        System.out.println();

        return possibleMaxJoltages.stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow();
    }

    private static List<Integer> parseInputLine(String line) {
        return Arrays.stream(line.split(""))
                .map(Integer::parseInt)
                .toList();
    }
}