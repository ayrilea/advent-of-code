package site.ayrilea.advent.solution.year2024.day18;

record Position(int row, int column) {

    static Position fromLine(String line) {
        int commaIndex = line.indexOf(',');

        int column = Integer.parseInt(line, 0, commaIndex, 10);
        int row = Integer.parseInt(line, commaIndex + 1, line.length(), 10);

        return new Position(row, column);
    }
}
