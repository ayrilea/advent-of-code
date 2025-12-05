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
                .filter(id -> freshIds.stream().anyMatch(range -> range.inRange(id)))
                .count();
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
}
