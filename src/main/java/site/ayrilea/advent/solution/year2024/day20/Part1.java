package site.ayrilea.advent.solution.year2024.day20;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SolutionMetadata(year = 2024, day = 20, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Solver solver = Solver.parseInput(input);
        int shortestPath = solver.getShortestPath();

        Map<Integer, Integer> cheatsByTimeSave = new HashMap<>();
        for (int step = 0; step < shortestPath; step++) {
            List<Integer> times = solver.getTimesWithCheat(step);
            for (Integer time : times) {
                int timeSave = shortestPath - time;
                if (timeSave > 0) {
                    cheatsByTimeSave.compute(timeSave, (_,v) -> v == null ? 1 : v + 1);
                }
                //System.out.println("Cheat on step [" + step + "] saves: " + (shortestPath - time));
            }
            if (step % 100 == 0) {
                System.out.println(step);
            }
        }

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
