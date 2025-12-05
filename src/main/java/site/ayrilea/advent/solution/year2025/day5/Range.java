package site.ayrilea.advent.solution.year2025.day5;

record Range(long startId, long endId) {

    boolean inRange(long id) {
        return id >= startId && id <= endId;
    }
}
