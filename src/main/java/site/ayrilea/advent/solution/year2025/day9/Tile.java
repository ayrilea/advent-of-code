package site.ayrilea.advent.solution.year2025.day9;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Tile(long column, long row) implements Comparable<Tile> {

    private static final Pattern PATTERN_TILE = Pattern.compile("(?<column>\\d+),(?<row>\\d+)");

    @Override
    public int compareTo(Tile o) {
        if (column == o.column) {
            if (row == o.row) {
                return 0;
            }
            return row < o.row ? -1 : 1;
        }
        return column < o.column ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tile tile = (Tile) o;
        return column == tile.column && row == tile.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    static Tile fromInput(String tile) {
        Matcher matcher = PATTERN_TILE.matcher(tile);
        if (matcher.matches()) {
            long column = Long.parseLong(matcher.group("column"));
            long row = Long.parseLong(matcher.group("row"));
            return new Tile(column, row);
        }
        throw new IllegalArgumentException("Invalid input tile: " + tile);
    }
}
