package site.ayrilea.advent.solution.year2024.day4;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.Map;

class Shared {

    private static final Map<Character, Integer> CHAR_TO_INT = Map.of(
            'X', 0,
            'M', 1,
            'A', 2,
            'S', 3);

    static int[][] parseInput(Input input) {
         return input.stream()
                .map(line -> line.split(""))
                .map(Arrays::stream)
                .map(letters -> letters
                        .map(l -> l.charAt(0))
                        .map(CHAR_TO_INT::get)
                        .mapToInt(i -> i)
                        .toArray())
                .toArray(int[][]::new);
    }

    static int sumCountForEachElement(int[][] values, CountForElement countForElement) {
        int count = 0;
        for (int row = 0; row < values.length; row++) {
            for (int column = 0; column < values[0].length; column++) {
                count += countForElement.apply(values, row, column);
            }
        }
        return count;
    }
}
