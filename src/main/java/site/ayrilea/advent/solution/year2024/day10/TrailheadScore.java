package site.ayrilea.advent.solution.year2024.day10;

@FunctionalInterface
public interface TrailheadScore {

    int calculate(int[][] map, int row, int column);
}
