package site.ayrilea.advent.solution.year2024.day12;

import site.ayrilea.advent.input.Input;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

class Shared {

    static int solve(Input input, Function<Region, Integer> priceForRegion) {
        String[][] pots = parseInput(input);

        List<Region> regions = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        for (int row = 0; row < pots.length; row++) {
            for (int column = 0; column < pots[0].length; column++) {
                if (visited.contains(new Position(row, column))) {
                    continue;
                }

                Region region = new Region();
                String plant = pots[row][column];

                exploreRegion(region, visited, plant, pots, row, column);

                regions.add(region);
            }
        }

        return regions.stream()
                .map(priceForRegion)
                .mapToInt(i -> i)
                .sum();
    }

    private static void exploreRegion(Region region, Set<Position> visited, String plant, String[][] pots, int row,
                              int column) {
        if (visited.contains(new Position(row, column))) {
            return;
        }
        visited.add(new Position(row, column));
        int newPerimeter = 0;
        //Up
        if (row == 0 || !plant.equals(pots[row - 1][column])) {
            newPerimeter++;
        } else {
            exploreRegion(region, visited, plant, pots, row - 1, column);
        }
        //Down
        if (row == pots.length - 1 || !plant.equals(pots[row + 1][column])) {
            newPerimeter++;
        } else {
            exploreRegion(region, visited, plant, pots, row + 1, column);
        }
        //Left
        if (column == 0 || !plant.equals(pots[row][column - 1])) {
            newPerimeter++;
        } else {
            exploreRegion(region, visited, plant, pots, row, column - 1);
        }
        //Right
        if (column == pots[0].length - 1 || !plant.equals(pots[row][column + 1])) {
            newPerimeter++;
        } else {
            exploreRegion(region, visited, plant, pots, row, column + 1);
        }
        region.addPlot(newPerimeter);
    }

    private static String[][] parseInput(Input input) {
        return input.stream()
                .map(line -> line.split(""))
                .toArray(String[][]::new);
    }
}
