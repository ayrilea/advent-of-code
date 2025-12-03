package site.ayrilea.advent.solution.year2025.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record Bank(List<Integer> batteries) {

    long maxJoltage(int numberOfBatteriesToEnable) {
        return maxJoltage(numberOfBatteriesToEnable, 0, Collections.emptyList());
    }

    private List<Integer> getMaxDigitIndexes(int startIndex, int endIndex) {
        List<Integer> maxDigitIndexes = new ArrayList<>();
        int maxDigit = -1;

        for (int i = startIndex; i <= endIndex; i++) {
            int battery = batteries.get(i);
            if (battery == maxDigit) {
                maxDigitIndexes.add(i);
            } else if (battery > maxDigit) {
                maxDigit = battery;
                maxDigitIndexes = new ArrayList<>();
                maxDigitIndexes.add(i);
            }
        }

        return maxDigitIndexes;
    }

    private long maxJoltage(int numberOfBatteriesToEnable, int startIndex, List<String> enabledBatteries) {
        //Base case - the desired number of batteries is enabled
        if (enabledBatteries.size() == numberOfBatteriesToEnable) {
            return Long.parseLong(String.join("", enabledBatteries));
        }

        //The Nth battery enabled must leave at least N - 1 batteries to its right to enable, so that the final number
        //of batteries enabled is numberOfBatteriesToEnable. For example, if enabling 6 batteries, with a bank size of
        //10, the 3rd battery must be located at or before index 6.
        //
        // [0][1][2][3][4][5][6][7][8][9]
        //                    ^
        //                    last possible index for 3rd battery of 6 (always giving room for 3 more after)
        int endIndex = batteries.size() - numberOfBatteriesToEnable + enabledBatteries.size();
        //Find all locations (indexes) within the bank of the max digit within the range:
        // [ starting at the specified startIndex passed in,
        //   up until the farthest right that this specific battery can go ]
        //
        List<Integer> maxDigitIndexes = getMaxDigitIndexes(startIndex, endIndex);

        //There will always be at least one maxDigitIndex, and all maxDigitIndexes will point to the same battery value,
        //so just read the first index in the list to retrieve the maxDigit.
        int maxDigit = batteries.get(maxDigitIndexes.getFirst());
        //Add this digit to the bank of enabled batteries.
        List<String> updatedEnabledBatteries = new ArrayList<>(enabledBatteries);
        updatedEnabledBatteries.add(String.valueOf(maxDigit));

        return maxDigitIndexes.stream()
                //Recursively find the max joltage for each maxDigit location, with the next battery starting one
                //location to the right
                .map(maxDigitIndex -> maxJoltage(numberOfBatteriesToEnable, maxDigitIndex + 1,
                        updatedEnabledBatteries))
                .mapToLong(l -> l)
                //Only return the maximum from the calculated joltages
                .max()
                .orElseThrow();
    }
}
