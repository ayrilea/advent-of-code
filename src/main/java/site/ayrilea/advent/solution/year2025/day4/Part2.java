package site.ayrilea.advent.solution.year2025.day4;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.HashSet;
import java.util.Set;

@SolutionMetadata(year = 2025, day = 4, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        Grid grid = Grid.fromInput(input);

        int removedTiles = 0;
        Set<Coordinate> accessibleTiles = grid.getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            removedTiles += accessibleTiles.size();

            grid.removeTiles(accessibleTiles);
            accessibleTiles = grid.getAccessibleTiles();
        }

        return removedTiles;
    }
}