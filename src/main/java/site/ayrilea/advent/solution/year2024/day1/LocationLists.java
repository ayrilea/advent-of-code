package site.ayrilea.advent.solution.year2024.day1;

import java.util.ArrayList;
import java.util.List;

public class LocationLists {

    private final List<Integer> firstLocationIds;
    private final List<Integer> secondLocationIds;

    private LocationLists(Builder builder) {
        this.firstLocationIds = builder.firstLocationIds
                .stream()
                .sorted()
                .toList();
        this.secondLocationIds = builder.secondLocationIds
                .stream()
                .sorted()
                .toList();
    }

    public List<Integer> getFirstLocationIds() {
        return firstLocationIds;
    }

    public List<Integer> getSecondLocationIds() {
        return secondLocationIds;
    }

    public static class Builder {

        private final List<Integer> firstLocationIds = new ArrayList<>();
        private final List<Integer> secondLocationIds = new ArrayList<>();

        public LocationLists build() {
            return new LocationLists(this);
        }

        public Builder locationPair(LocationPair value) {
            firstLocationIds.add(value.firstLocationId());
            secondLocationIds.add(value.secondLocationId());
            return this;
        }
    }
}
