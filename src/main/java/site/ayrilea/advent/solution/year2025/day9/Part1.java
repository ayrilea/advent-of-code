package site.ayrilea.advent.solution.year2025.day9;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.List;

@SolutionMetadata(year = 2025, day = 9, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        List<Tile> tiles = input.stream()
                .map(Tile::fromInput)
                .toList();

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                pairs.add(new Pair(tiles.get(i), tiles.get(j)));
            }
        }

        return pairs.stream()
                .map(Part1::area)
                .mapToLong(l -> l)
                .max()
                .orElseThrow();
    }

    private static long area(Pair pair) {
        return (Math.abs(pair.first().column() - pair.second().column()) + 1) *
                (Math.abs(pair.first().row() - pair.second().row()) + 1);
    }
}