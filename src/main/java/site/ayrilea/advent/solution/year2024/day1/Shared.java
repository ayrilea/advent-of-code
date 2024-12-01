package site.ayrilea.advent.solution.year2024.day1;

import site.ayrilea.advent.input.Input;

class Shared {

    static LocationLists parseInput(Input input) {
        LocationLists.Builder builder = new LocationLists.Builder();

        input.stream()
                .map(LocationPair::parseInputLine)
                .forEach(builder::locationPair);

        return builder.build();
    }
}
