package site.ayrilea.advent.solution.year2024.day3;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SolutionMetadata(year = 2024, day = 3, part = 2)
public class Part2 implements Solution<Integer> {

    private static final Pattern PATTERN = Pattern.compile(
            "(?<condition>do\\(\\)|don't\\(\\))|mul\\((?<first>\\d+),(?<second>\\d+)\\)");

    @Override
    public Integer solve(Input input) {
        AtomicBoolean enabled = new AtomicBoolean(true);
        return input.stream()
                .map(PATTERN::matcher)
                .flatMap(Matcher::results)
                .map(result -> {
                    String condition = result.group("condition");
                    if (condition == null && enabled.get()) {
                        return Integer.parseInt(result.group("first")) *
                                Integer.parseInt(result.group("second"));
                    }
                    if ("do()".equals(condition)) {
                        enabled.set(true);
                    } else if ("don't()".equals(condition)) {
                        enabled.set(false);
                    } else {
                        //throw new IllegalStateException("Invalid condition: [" + condition + "]");
                    }
                    return 0;
                })
                .mapToInt(Integer::intValue)
                .sum();
    }
}
