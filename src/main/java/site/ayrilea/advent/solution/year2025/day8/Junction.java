package site.ayrilea.advent.solution.year2025.day8;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record Junction(long x, long y, long z) {

    private static final Pattern PATTERN_JUNCTION = Pattern.compile("(?<x>\\d+),(?<y>\\d+),(?<z>\\d+)");

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Junction junction = (Junction) o;
        return x == junction.x && y == junction.y && z == junction.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    static Junction fromInput(String junction) {
        Matcher matcher = PATTERN_JUNCTION.matcher(junction);
        if (matcher.matches()) {
            long x = Long.parseLong(matcher.group("x"));
            long y = Long.parseLong(matcher.group("y"));
            long z = Long.parseLong(matcher.group("z"));
            return new Junction(x, y, z);
        }
        throw new IllegalArgumentException("Invalid input junction: " + junction);
    }
}
