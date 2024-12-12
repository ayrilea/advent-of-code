package site.ayrilea.advent.solution.year2024.day12;

class Region {

    private int area;
    private int perimeter;

    void addPlot(int perimeter) {
        area++;
        this.perimeter += perimeter;
    }

    int getArea() {
        return area;
    }

    int getPerimeter() {
        return perimeter;
    }
}
