package site.ayrilea.advent.solution.year2025.day9;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;
import java.util.stream.Collectors;

@SolutionMetadata(year = 2025, day = 9, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part2::validPair);
    }

    private static boolean validPair(Pair pair, Tiles tiles) {
        Set<Tile> greenPerimeter = tiles.getPerimeterTiles();
        Set<Tile> pairPerimeter = pair.getPerimeter();

        for (Tile tile : pairPerimeter) {
            if (!isInside(tile, greenPerimeter)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isInside(Tile tile, Set<Tile> perimeter) {
        if (perimeter.contains(tile)) {
            return true;
        }

        List<Tile> sameColumnTiles = perimeter.stream()
                .filter(t -> t.column() == tile.column())
                .sorted()
                .toList();
        List<Tile> sameRowTiles = perimeter.stream()
                .filter(t -> t.row() == tile.row())
                .sorted()
                .toList();

        if (tile.row() < sameColumnTiles.getFirst().row()) {
            return false;
        }
        if (tile.row() > sameColumnTiles.getLast().row()) {
            return false;
        }
        if (tile.column() < sameRowTiles.getFirst().column()) {
            return false;
        }
        if (tile.column() > sameRowTiles.getLast().column()) {
            return false;
        }

        int sameColumnBeforeIndex = 0;
        int sameColumnAfterIndex = 1;
        while (!(tile.row() > sameColumnTiles.get(sameColumnBeforeIndex).row() &&
                tile.row() < sameColumnTiles.get(sameColumnAfterIndex).row())) {
            sameColumnBeforeIndex++;
            sameColumnAfterIndex++;
        }

        boolean columnInside = false;
        for (int columnIndex = 0; columnIndex < sameColumnAfterIndex; columnIndex++) {
            Tile current = sameColumnTiles.get(columnIndex);
            if ((current.row() + 1 == sameColumnTiles.get(columnIndex + 1).row())) {
                columnInside = false;
            } else {
                columnInside = !columnInside;
            }
        }

        int sameRowBeforeIndex = 0;
        int sameRowAfterIndex = 1;
        while (!(tile.column() > sameRowTiles.get(sameRowBeforeIndex).column() &&
                tile.column() < sameRowTiles.get(sameRowAfterIndex).column())) {
            sameRowBeforeIndex++;
            sameRowAfterIndex++;
        }
        boolean rowInside = false;
        for (int rowIndex = 0; rowIndex < sameRowAfterIndex; rowIndex++) {
            Tile current = sameRowTiles.get(rowIndex);
            if ((current.column() + 1 == sameRowTiles.get(rowIndex + 1).column())) {
                rowInside = false;
            } else {
                rowInside = !rowInside;
            }
        }

        return columnInside && rowInside;
    }
}