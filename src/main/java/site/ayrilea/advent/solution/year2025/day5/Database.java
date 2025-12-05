package site.ayrilea.advent.solution.year2025.day5;

import site.ayrilea.advent.input.Input;

import java.util.*;

class Database {

    private final Set<Long> availableIds;
    private final Set<Range> freshIds;

    private Database(Set<Long> availableIds, Set<Range> freshIds) {
        this.availableIds = availableIds;
        this.freshIds = collapseRanges(freshIds);
    }

    long numberOfAvailableFreshIds() {
        return availableIds.stream()
                .filter(id -> freshIds.stream()
                        .anyMatch(range -> range.inRange(id)))
                .count();
    }

    long numberOfFreshIds() {
        return freshIds.stream()
                .map(Range::size)
                .mapToLong(l -> l)
                .sum();
    }

    static Database fromInput(Input input) {
        Set<Long> availableIds = new HashSet<>();
        Set<Range> freshIds = new HashSet<>();

        List<String> lines = input.list();
        int lineIndex = 0;
        //Process fresh ID ranges
        while (!Objects.equals("", lines.get(lineIndex))) {
            String[] parts = lines.get(lineIndex).split("-");
            freshIds.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
            lineIndex++;
        }
        //Process empty line
        lineIndex++;
        //Process available IDs
        while (lineIndex < lines.size()) {
            availableIds.add(Long.parseLong(lines.get(lineIndex)));
            lineIndex++;
        }

        return new Database(availableIds, freshIds);
    }

    private static Set<Range> collapseRanges(Set<Range> ranges) {
        Set<Range> collapsedRanges = new HashSet<>();
        List<Range> sortedRanges = ranges.stream().sorted().toList();

        Range current = sortedRanges.getFirst();
        for (int i = 1; i < sortedRanges.size(); i++) {
            Range next = sortedRanges.get(i);
            //Next range starts beyond the current range, so must end beyond it too. Add the current range to the final
            //list, then use the next range as the new baseline.
            if (next.startId() > current.endId()) {
                collapsedRanges.add(current);
                current = next;
            } else {
                //Next range overlaps with current range. Fold the two ranges together, starting at the current startId
                //(which must be lower due to the pre-sort), up to the maximum of the end IDs (which could be either).
                current = new Range(current.startId(), Math.max(current. endId(), next.endId()));
            }
        }
        //Add the final range being processed.
        collapsedRanges.add(current);

        return collapsedRanges;
    }
}
