package site.ayrilea.advent.solution.year2024.day8;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.HashSet;
import java.util.Set;

import static site.ayrilea.advent.solution.year2024.day8.Shared.isInMap;

@SolutionMetadata(year = 2024, day = 8, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part2::pairToAntinodes);
    }

    private static Set<Position> pairToAntinodes(Pair pair, int mapMaxRow, int mapMaxColumn) {
        Set<Position> antinodes = new HashSet<>();

        Position first = pair.first();
        antinodes.add(first);
        Position second = pair.second();
        antinodes.add(second);

        int rowDifference = Math.abs(first.row() - second.row());
        int columnDifference = Math.abs(first.column() - second.column());

        int rowMin = Math.min(first.row(), second.row());
        int rowMax = Math.max(first.row(), second.row());

        int columnMin = Math.min(first.column(), second.column());
        int columnMax = Math.max(first.column(), second.column());

        if (first.column() <= second.column()) {
            //Top left antinode and harmonics
            int row = rowMin - rowDifference;
            int column = columnMin - columnDifference;
            while (isInMap(row, column, mapMaxRow, mapMaxColumn)) {
                antinodes.add(new Position(row, column));
                row -= rowDifference;
                column -= columnDifference;
            }

            //Bottom right antinode and harmonics
            row = rowMax + rowDifference;
            column = columnMax + columnDifference;
            while (isInMap(row, column, mapMaxRow, mapMaxColumn)) {
                antinodes.add(new Position(row, column));
                row += rowDifference;
                column += columnDifference;
            }
        } else {
            //Top right antinode and harmonics
            int row = rowMin - rowDifference;
            int column = columnMax + columnDifference;
            while (isInMap(row, column, mapMaxRow, mapMaxColumn)) {
                antinodes.add(new Position(row, column));
                row -= rowDifference;
                column += columnDifference;
            }

            //Bottom left antinode and harmonics
            row = rowMax + rowDifference;
            column = columnMin - columnDifference;
            while (isInMap(row, column, mapMaxRow, mapMaxColumn)) {
                antinodes.add(new Position(row, column));
                row += rowDifference;
                column -= columnDifference;
            }
        }

        return antinodes;
    }
}