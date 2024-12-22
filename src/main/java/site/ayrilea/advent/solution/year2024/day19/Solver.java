package site.ayrilea.advent.solution.year2024.day19;

import site.ayrilea.advent.input.Input;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solver {

    private final Set<String> designs;
    private final Set<String> patterns;

    Solver(Input input) {
        List<String> lines = input.list();
        designs = lines.subList(2, lines.size())
                .stream()
                .collect(Collectors.toUnmodifiableSet());
        patterns = Set.of(lines.getFirst().split(","));
    }

    int countPossibleDesigns() {
        return (int) designs.stream()
                .map(this::isPossible)
                .count();
    }

    private boolean isPossible(String design) {
        return true;
    }
}
