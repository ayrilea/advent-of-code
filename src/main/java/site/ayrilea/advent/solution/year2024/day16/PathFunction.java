package site.ayrilea.advent.solution.year2024.day16;

import java.util.Set;

@FunctionalInterface
interface PathFunction {

    int apply(Set<Position> walls, Position end, Node start);
}
