package site.ayrilea.advent.solution.year2024.day8;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Set;

@SolutionMetadata(year = 2024, day = 8, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part1::pairToAntinodes);
    }

    private static Set<Position> pairToAntinodes(Pair pair, int mapRowMax, int mapColumnMax) {
        Position first = pair.first();
        Position second = pair.second();

        int rowDifference = Math.abs(first.row() - second.row());
        int columnDifference = Math.abs(first.column() - second.column());

        int rowMin = Math.min(first.row(), second.row());
        int rowMax = Math.max(first.row(), second.row());

        int columnMin = Math.min(first.column(), second.column());
        int columnMax = Math.max(first.column(), second.column());

        if (first.column() <= second.column()) {
            return Set.of(
                    new Position(rowMin - rowDifference, columnMin - columnDifference),
                    new Position(rowMax + rowDifference, columnMax + columnDifference));
        } else {
            return Set.of(
                    new Position(rowMin - rowDifference, columnMax + columnDifference),
                    new Position(rowMax + rowDifference, columnMin - columnDifference));
        }
    }
}
