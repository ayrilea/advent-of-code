package site.ayrilea.advent.solution.year2024.day5;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Shared {

    static boolean isOrdered(List<Integer> update, List<Rule> rules) {
        return rules.stream().allMatch(rule -> rule.appliesTo(update));
    }

    static List<Rule> readRules(Input input) {
        return input.stream()
                .filter(line -> line.contains("|"))
                .map(line -> line.split("\\|"))
                .map(Rule::fromInputParts)
                .toList();
    }

    static Stream<List<Integer>> readUpdates(Input input) {
        return input.stream()
                .filter(line -> line.contains(","))
                .map(line -> line.split(","))
                .map(parts -> Arrays.stream(parts)
                        .map(Integer::parseInt)
                        .toList());
    }

    record Rule(int before, int after) {

        private boolean appliesTo(List<Integer> update) {
            int beforeIndex = update.indexOf(before);
            int afterIndex = update.indexOf(after);
            if (beforeIndex == -1 || afterIndex == -1) {
                return true;
            }
            return beforeIndex < afterIndex;
        }

        private static Rule fromInputParts(String[] parts) {
            return new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
    }
}
