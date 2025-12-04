package site.ayrilea.advent.solution.year2025.day4;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Grid {

    private final Boolean[][] grid;

    private Grid(Boolean[][] grid) {
        this.grid = grid;
    }

    Set<Coordinate> getAccessibleTiles() {
        Set<Coordinate> accessibleTiles = new HashSet<>();

        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y] && isAccessible(x, y)) {
                    accessibleTiles.add(new Coordinate(x, y));
                }
            }
        }

        return accessibleTiles;
    }

    void removeTiles(Set<Coordinate> tiles) {
        tiles.forEach(tile -> grid[tile.x()][tile.y()] = false);
    }

    private boolean isAccessible(int x, int y) {
        int adjacentRolls = 0;

        if (x > 0 && y > 0 && grid[x - 1][y - 1]) {
            adjacentRolls++;
        }
        if (y > 0 && grid[x][y - 1]) {
            adjacentRolls++;
        }
        if (x < grid[0].length - 1 && y > 0 && grid[x + 1][y - 1]) {
            adjacentRolls++;
        }
        if (x < grid[0].length - 1 && grid[x + 1][y]) {
            adjacentRolls++;
        }
        if (x < grid[0].length - 1 && y < grid.length - 1 && grid[x + 1][y + 1]) {
            adjacentRolls++;
        }
        if (y < grid.length - 1 && grid[x][y + 1]) {
            adjacentRolls++;
        }
        if (x > 0 && y < grid.length - 1 && grid[x - 1][y + 1]) {
            adjacentRolls++;
        }
        if (x > 0 && grid[x - 1][y]) {
            adjacentRolls++;
        }

        return adjacentRolls < 4;
    }

    static Grid fromInput(Input input) {
        return new Grid(input.stream()
                .map(line -> Arrays.stream(line.split(""))
                        .map(c -> Objects.equals(c, "@"))
                        .toArray(Boolean[]::new))
                .toArray(Boolean[][]::new));
    }
}
