package site.ayrilea.advent.solution.year2024.day10;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;

public class Shared {

    static int[][] parseInput(Input input) {
        return input.stream()
                .map(line -> line.split(""))
                .map(parts -> Arrays.stream(parts)
                        .map(Integer::parseInt)
                        .mapToInt(i -> i)
                        .toArray())
                .toArray(int[][]::new);
    }
}
