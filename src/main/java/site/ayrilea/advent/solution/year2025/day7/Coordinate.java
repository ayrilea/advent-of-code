package site.ayrilea.advent.solution.year2025.day7;

record Coordinate(int row, int column) {

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Coordinate(int otherRow, int otherColumn)) {
            return row == otherRow && column == otherColumn;
        }
        return false;
    }
}