package site.ayrilea.advent.solution.year2024.day5;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.LinkedList;
import java.util.List;

import static site.ayrilea.advent.solution.year2024.day5.Shared.*;

@SolutionMetadata(year = 2024, day = 5, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        List<Rule> rules = readRules(input);
        return readUpdates(input)
                .filter(update -> !isOrdered(update, rules))
                .map(update -> reorder(update, rules))
                .map(update -> update.get(update.size() / 2))
                .mapToInt(i -> i)
                .sum();
    }

    private static List<Integer> reorder(List<Integer> update, List<Rule> rules) {
        List<Integer> temp = new LinkedList<>(update);
        while (!isOrdered(temp, rules)) {
            rules.forEach(rule -> reorder(temp, rule));
        }
        return List.copyOf(temp);
    }

    private static List<Integer> reorder(List<Integer> update, Rule rule) {
        int beforeIndex = update.indexOf(rule.before());
        int afterIndex = update.indexOf(rule.after());

        //One or both pages that are part of the rule are not present in the update
        if (beforeIndex == -1 || afterIndex == -1) {
            return update;
        }
        //Both pages that are part of the rule are present but already in order
        if (beforeIndex < afterIndex) {
            return update;
        }

        //Swap out of order pages
        update.set(beforeIndex, rule.after());
        update.set(afterIndex, rule.before());
        return update;
    }
}