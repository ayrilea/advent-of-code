package site.ayrilea.advent.solution.year2025.day8;

import java.util.Objects;

record Pair(Junction first, Junction second) {

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

    boolean contains(Junction junction) {
        return Objects.equals(first, junction) || Objects.equals(second, junction);
    }
}
