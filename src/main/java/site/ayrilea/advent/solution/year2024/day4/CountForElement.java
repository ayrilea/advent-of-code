package site.ayrilea.advent.solution.year2024.day4;

@FunctionalInterface
public interface CountForElement {

    int apply(int[][] values, int row, int column);
}
