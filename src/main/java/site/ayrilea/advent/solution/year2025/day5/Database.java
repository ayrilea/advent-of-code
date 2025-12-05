package site.ayrilea.advent.solution.year2025.day5;

import site.ayrilea.advent.input.Input;

import java.util.*;

class Database {

    private final Set<Long> availableIds;
    private final Set<Range> freshIds;

    private Database(Set<Long> availableIds, Set<Range> freshIds) {
        this.availableIds = availableIds;
        this.freshIds = freshIds;
    }

    long numberOfAvailableFreshIds() {
        return availableIds.stream()
                .filter(id -> freshIds.stream()
                        .anyMatch(range -> range.inRange(id)))
                .count();
    }

    long numberOfFreshIds() {
        return collapseRanges(freshIds).stream()
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
            String line = lines.get(lineIndex);
            String[] parts = line.split("-");
            long startId = Long.parseLong(parts[0]);
            long endId = Long.parseLong(parts[1]);
            freshIds.add(new Range(startId, endId));
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

    private static List<Range> collapseRanges(Set<Range> ranges) {
        List<Range> collapsedRanges = new ArrayList<>();
        List<Range> sortedRanges = ranges.stream().sorted().toList();

        Range current = sortedRanges.getFirst();
        for (int i = 1; i < sortedRanges.size(); i++) {
            Range next = sortedRanges.get(i);
            if (next.startId() > current.endId()) {
                collapsedRanges.add(current);
                current = next;
            } else {
                current = new Range(current.startId(), Math.max(current. endId(), next.endId()));
            }
        }
        collapsedRanges.add(current);

        return collapsedRanges;
    }
}
