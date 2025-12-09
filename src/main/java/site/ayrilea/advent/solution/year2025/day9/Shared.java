package site.ayrilea.advent.solution.year2025.day9;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;

class Shared {

    static long solve(Input input, BiPredicate<Pair, Tiles> filter) {
        Tiles tiles = parseInput(input);
        List<Pair> pairs = getPairs(tiles.getRedTiles());

        return pairs.stream()
                .filter(pair -> filter.test(pair, tiles))
                .map(Pair::getArea)
                .mapToLong(l -> l)
                .max()
                .orElseThrow();
    }

    static Set<Tile> tilesBetween(Tile first, Tile second) {
        Set<Tile> tiles = new HashSet<>();

        long minColumn = Math.min(first.column(), second.column());
        long maxColumn = Math.max(first.column(), second.column());
        long minRow = Math.min(first.row(), second.row());
        long maxRow = Math.max(first.row(), second.row());

        for (long row = minRow; row <= maxRow; row++) {
            for (long column = minColumn; column <= maxColumn; column++) {
                tiles.add(new Tile(column, row));
            }
        }

        tiles.remove(first);
        tiles.remove(second);
        return tiles;
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
        //Read red tiles directly from input
        List<Tile> redTiles = input.stream()
                .map(Tile::fromInput)
                .toList();

        //Calculate green border tiles connecting red tiles
        Set<Tile> greenTiles = new HashSet<>();
        Tile current = redTiles.getFirst();
        for (int i = 1; i < redTiles.size(); i++) {
            Tile next = redTiles.get(i);
            greenTiles.addAll(tilesBetween(current, next));
            current = next;
        }
        greenTiles.addAll(tilesBetween(current, redTiles.getFirst()));

        return new Tiles(greenTiles, redTiles);
    }
}
