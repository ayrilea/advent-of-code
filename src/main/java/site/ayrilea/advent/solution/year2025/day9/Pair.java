package site.ayrilea.advent.solution.year2025.day9;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static site.ayrilea.advent.solution.year2025.day9.Shared.tilesBetween;

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

    Set<Tile> getPerimeter() {
        Tile oppositeCornerOne = new Tile(first.column(), second.row());
        Tile oppositeCornerTwo = new Tile(second.column(), first.row());

        Set<Tile> perimeter = new HashSet<>();
        perimeter.addAll(tilesBetween(first, oppositeCornerOne));
        perimeter.addAll(tilesBetween(oppositeCornerOne, second));
        perimeter.addAll((tilesBetween(second, oppositeCornerTwo)));
        perimeter.addAll(tilesBetween(oppositeCornerTwo, first));
        perimeter.add(first);
        perimeter.add(second);
        perimeter.add(oppositeCornerOne);
        perimeter.add(oppositeCornerTwo);

        return perimeter;
    }
}
