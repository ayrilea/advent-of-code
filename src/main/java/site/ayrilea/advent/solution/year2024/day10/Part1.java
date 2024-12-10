package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.LinkedHashSet;
import java.util.Set;

@SolutionMetadata(year = 2024, day = 10, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, Part1::trailheadScore);
    }

    private static int trailheadScore(int[][] map, int row, int column) {
        Set<Position> trailheads = new LinkedHashSet<>();
        getTrailheads(map, row, column, 0, trailheads);
        return trailheads.size();
    }

    private static void getTrailheads(int[][] map, int row, int column, int height, Set<Position> trailheads) {
        if (height == 9) {
            trailheads.add(new Position(row, column));
        }

        //Up
        if (row - 1 >= 0 && map[row - 1][column] == height + 1) {
            getTrailheads(map, row - 1, column, height + 1, trailheads);
        }
        //Down
        if (row + 1 <= map.length - 1 && map[row + 1][column] == height + 1) {
            getTrailheads(map, row + 1, column, height + 1, trailheads);
        }
        //Left
        if (column - 1 >= 0 && map[row][column - 1] == height + 1) {
            getTrailheads(map, row, column - 1, height + 1, trailheads);
        }
        //Right
        if (column + 1 <= map[0].length - 1 && map[row][column + 1] == height + 1) {
            getTrailheads(map, row, column + 1, height + 1, trailheads);
        }
    }
}
