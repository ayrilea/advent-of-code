package site.ayrilea.advent.solution.year2024.day1;

public record LocationPair(int firstLocationId, int secondLocationId) {

    public static LocationPair parseInputLine(String line) {
        String[] locationIds = line.split(" ");
        return new LocationPair(Integer.parseInt(locationIds[0]), Integer.parseInt(locationIds[3]));
    }
}
