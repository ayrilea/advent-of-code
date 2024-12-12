package site.ayrilea.advent.solution.year2024.day12;

import java.util.HashSet;
import java.util.Set;

class Region {

    private final Set<Position> positions;

    private int perimeter;

    Region() {
        perimeter = 0;
        positions = new HashSet<>();
    }

    void addPlot(int perimeter, Position position) {
        this.perimeter += perimeter;
        positions.add(position);
    }

    int getArea() {
        return positions.size();
    }

    int getPerimeter() {
        return perimeter;
    }

    Set<Position> getPositions() {
        return  positions;
    }
}
