package site.ayrilea.advent.solution.year2025.day5;

record Range(long startId, long endId) implements Comparable<Range> {

    @Override
    public int compareTo(Range o) {
        if (startId == o.startId) {
            if (endId == o.endId) {
                return 0;
            }
            return endId < o.endId ? -1 : 1;
        }
        return startId < o.startId ? -1 : 1;
    }

    boolean inRange(long id) {
        return id >= startId && id <= endId;
    }

    long size() {
        return endId - startId + 1;
    }
}
