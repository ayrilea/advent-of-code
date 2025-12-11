package site.ayrilea.advent.solution.year2025.day11;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

@SolutionMetadata(year = 2025, day = 11, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Map<String, Device> devices = Shared.parseInput(input);
        return numberOfPaths(Set.of(devices.get("dac"), devices.get("fft")), devices.get("out"), devices.get("svr"),
                new ArrayList<>());
    }

    private static int numberOfPaths(Set<Device> required, Device end, Device current, List<Device> path) {
        if (Objects.equals(current, end)) {
            if (new HashSet<>(path).containsAll(required)) {
                return 1;
            }
            return 0;
        }

        int paths = 0;
        path.add(current);
        for (Device output : current.outputs()) {
            paths += numberOfPaths(required, end, output, path);
        }
        path.removeLast();
        return paths;
    }
}