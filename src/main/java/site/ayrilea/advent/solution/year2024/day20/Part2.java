package site.ayrilea.advent.solution.year2024.day20;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Comparator;
import java.util.Map;

@SolutionMetadata(year = 2024, day = 20, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Solver solver = Solver.parseInput(input);
        Map<Integer, Integer> cheatsByTimeSave = solver.getCheatsByTimeSave(20);

        cheatsByTimeSave.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .forEach(entry -> System.out.println(entry.getValue() + " saving " + entry.getKey() + " seconds"));
        return cheatsByTimeSave.entrySet()
                .stream()
                .filter(entry -> entry.getKey() >= 100)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}