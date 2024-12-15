package site.ayrilea.advent.solution.year2024.day15;

class Robot {

    private int column;
    private int row;

    Robot(int column, int row) {
        this.column = column;
        this.row = row;
    }

    int getColumn() {
        return column;
    }

    int getRow() {
        return row;
    }

    void move(Move move) {
        switch (move) {
            case DOWN -> row++;
            case LEFT -> column--;
            case RIGHT -> column++;
            case UP -> row--;
        }
    }
}
