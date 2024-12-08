package site.ayrilea.advent.solution.year2024.day8;

import java.util.Set;

@FunctionalInterface
interface PairToAntinodes {

    Set<Position> apply(Pair pair, int mapRowMax, int mapColumnMax);
}
