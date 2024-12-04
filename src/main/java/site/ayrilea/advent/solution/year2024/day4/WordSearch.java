package site.ayrilea.advent.solution.year2024.day4;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.Map;

public class WordSearch {

    private static final Map<Character, Integer> CHAR_TO_INT = Map.of(
            'X', 0,
            'M', 1,
            'A', 2,
            'S', 3);

    private final int[][] words;

    private WordSearch(int[][] words) {
        this.words = words;
    }

    public int getCrossMasCount() {
        int crossCount = 0;
        for (int row = 0; row < words.length; row++) {
            for (int column = 0; column < words[0].length; column++) {
                //Cross is centered on A
                if (words[row][column] == 2)  {
                    //Centre A can not be on boundary
                    if (row > 0 && row < words.length - 1 && column > 0 && column < words[0].length - 1) {
                        int topLeft = words[row - 1][column - 1];
                        int topRight = words[row - 1][column + 1];
                        int bottomLeft = words[row + 1][column - 1];
                        int bottomRight = words[row + 1][column + 1];

                        if (isMas(topLeft, bottomRight) && isMas(topRight, bottomLeft)) {
                            crossCount++;
                        }
                    }
                }
            }
        }
        return crossCount;
    }

    private boolean isMas(int first, int second) {
        return first == 1 && second == 3 || first == 3 && second == 1;
    }

    public int getXmasCount() {
        int wordCount = 0;
        for (int row = 0; row < words.length; row++) {
            for (int column = 0; column < words[0].length; column++) {
                int countForPosition = 0;
                int first = words[row][column];
                if (first == 0) {
                    if (row > 2) {
                        //Up Left
                        if (column > 2) {
                            if (words[row - 1][column - 1] == 1 && words[row - 2][column - 2] == 2 &&
                                    words[row - 3][column - 3] == 3) {
                                countForPosition++;
                            }
                        }
                        //Up
                        if (words[row - 1][column] == 1 && words[row - 2][column] == 2 && words[row - 3][column] == 3) {
                            countForPosition++;
                        }
                        //Up Right
                        if (column < words[0].length - 3) {
                            if (words[row - 1][column + 1] == 1 && words[row - 2][column + 2] == 2 &&
                                    words[row - 3][column + 3] == 3) {
                                countForPosition++;
                            }
                        }
                    }
                    //Left
                    if (column > 2) {
                        if (words[row][column - 1] == 1 && words[row][column - 2] == 2 && words[row][column - 3] == 3) {
                            countForPosition++;
                        }
                    }
                    //Right
                    if (column < words[0].length - 3) {
                        if (words[row][column + 1] == 1 && words[row][column + 2] == 2 && words[row][column + 3] == 3) {
                            countForPosition++;
                        }
                    }
                    if (row < words.length - 3) {
                        //Down Left
                        if (column > 2) {
                            if (words[row + 1][column - 1] == 1 && words[row + 2][column - 2] == 2 &&
                                    words[row + 3][column - 3] == 3) {
                                countForPosition++;
                            }
                        }
                        //Down
                        if (words[row + 1][column] == 1 && words[row + 2][column] == 2 && words[row + 3][column] == 3) {
                            countForPosition++;
                        }
                        //Down Right
                        if (column < words[0].length - 3) {
                            if (words[row + 1][column + 1] == 1 && words[row + 2][column + 2] == 2 &&
                                    words[row + 3][column + 3] == 3) {
                                countForPosition++;
                            }
                        }
                    }
                }
                wordCount += countForPosition;
            }
        }
        return wordCount;
    }

    public static WordSearch fromInput(Input input) {
        int[][] words = input.stream()
                .map(line -> line.split(""))
                .map(Arrays::stream)
                .map(letters -> letters
                        .map(l -> l.charAt(0))
                        .map(CHAR_TO_INT::get)
                        .mapToInt(i -> i)
                        .toArray())
                .toArray(int[][]::new);

        return new WordSearch(words);
    }
}
