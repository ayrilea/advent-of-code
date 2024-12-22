package site.ayrilea.advent.solution.year2024.day18;

import site.ayrilea.advent.input.Input;

import java.util.Set;

import static java.util.stream.Collectors.toUnmodifiableSet;

class Shared {

    static Set<Position> parseInput(Input input) {
        return input.list().subList(0, 1024).stream()
                .map(Position::fromLine)
                .collect(toUnmodifiableSet());
    }
}
