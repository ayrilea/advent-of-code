package site.ayrilea.advent.solution.year2025.day9;

import java.util.Objects;

record Pair(Tile first, Tile second) {

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second) ||
                Objects.equals(first, pair.second) && Objects.equals(second, pair.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    long getArea() {
        return (Math.abs(first.column() - second.column()) + 1) *
                (Math.abs(first.row() - second.row()) + 1);
    }
}
