package site.ayrilea.advent.solution.year2025.day9;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

class Shared {

    static long solve(Input input, BiPredicate<Pair, Tiles> filter) {
        Tiles tiles = parseInput(input);
        List<Pair> pairs = getPairs(tiles.redTiles());

        return pairs.stream()
                .filter(pair -> filter.test(pair, tiles))
                .map(Pair::getArea)
                .mapToLong(l -> l)
                .max()
                .orElseThrow();
    }

    private static List<Pair> getPairs(List<Tile> tiles) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                pairs.add(new Pair(tiles.get(i), tiles.get(j)));
            }
        }
        return pairs;
    }

    private static Tiles parseInput(Input input) {
        return new Tiles(Collections.emptyList(), input.stream()
                .map(Tile::fromInput)
                .toList());
    }
}
