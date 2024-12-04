package site.ayrilea.advent.solution.year2024.day3;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SolutionMetadata(year = 2024, day = 3, part = 1)
public class Part1 implements Solution<Integer> {

    private static final Pattern PATTERN = Pattern.compile("mul\\((?<first>\\d+),(?<second>\\d+)\\)");

    @Override
    public Integer solve(Input input) {
        return input.stream()
                .map(PATTERN::matcher)
                .flatMap(Matcher::results)
                .map(result -> Integer.parseInt(result.group("first")) *
                        Integer.parseInt(result.group("second")))
                .mapToInt(Integer::intValue)
                .sum();
    }
}
