package site.ayrilea.advent.solution.year2024.day6;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LabMap {

    private static final int MAX_STEP_COUNT = 1_000_000;

    private final Set<GuardPosition> guardPositions;
    private final boolean[][] locations;

    private int newObstructionCausingALoopCount;
    private int guardX;
    private int guardXVelocity;
    private int guardY;
    private int guardYVelocity;

    private LabMap(boolean[][] locations, int guardX, int guardY) {
        this.guardX = guardX;
        this.guardY = guardY;
        this.locations = locations;

        guardPositions = new HashSet<>();

        //Facing up
        guardXVelocity = -1;
        guardYVelocity = 0;

        newObstructionCausingALoopCount = 0;
    }

    int getGuardPositionsCount() {
        simulateGuardPositions();
        return guardPositions.size();
    }

    int getNewObstructionsCausingALoopCount() {
        simulateEachNewObstruction();
        return newObstructionCausingALoopCount;
    }

    static LabMap parseInput(Input input) {
        List<String> lines = input.list();

        boolean[][] locations = new boolean[lines.size()][lines.getFirst().length()];
        int guardX = 0;
        int guardY = 0;

        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.getFirst().length(); column++) {
                char current = lines.get(row).charAt(column);
                if ('#' == current) {
                    locations[row][column] = '#' == lines.get(row).charAt(column);
                } else if ('^' == current) {
                    guardX = row;
                    guardY = column;
                }
            }
        }

        return new LabMap(locations, guardX, guardY);
    }

    private boolean isObstructionInFront() {
        try {
            return locations[guardX + guardXVelocity][guardY + guardYVelocity];
        } catch (ArrayIndexOutOfBoundsException e) {
            //Looking off the map means no obstruction
            return false;
        }
    }

    private void move() {
        guardX += guardXVelocity;
        guardY += guardYVelocity;
    }

    private void simulateGuardPositions() {
        int maxRow = locations.length - 1;
        int maxCol = locations[0].length - 1;

        while (guardX >= 0 && guardX <= maxRow && guardY >= 0 && guardY <= maxCol) {
            //Add position (inclusive of starting position, exclusive of end, as that will break out of loop)
            guardPositions.add(new GuardPosition(guardX, guardY));

            if (isObstructionInFront()) {
                //Turn if obstructed
                turn();
            } else {
                //Else move one position
                move();
            }
        }
    }

    private void simulateEachNewObstruction() {
        for (int row = 0; row < locations.length; row++) {
            for (int column = 0; column < locations[0].length; column++) {
                simulateNewObstruction(row, column);
            }
        }
    }

    private void simulateNewObstruction(int obstructionRow, int obstructionColumn) {
        boolean[][] tempLocations = Arrays.stream(locations)
                .map(boolean[]::clone)
                .toArray(_ -> locations.clone());
        tempLocations[obstructionRow][obstructionColumn] = true;

        int tmpGuardX = guardX;
        int tmpGuardXVelocity = guardXVelocity;
        int tmpGuardY = guardY;
        int tmpGuardYVelocity = guardYVelocity;

        int maxRow = tempLocations.length - 1;
        int maxCol = tempLocations[0].length - 1;

        int stepCount = 0;
        while (tmpGuardX >= 0 && tmpGuardX <= maxRow && tmpGuardY >= 0 && tmpGuardY <= maxCol) {
            if (stepCount >= MAX_STEP_COUNT) {
                //Most likely in a loop
                newObstructionCausingALoopCount++;
                break;
            }

            boolean isObstructionInFront = false;
            try {
                isObstructionInFront = tempLocations[tmpGuardX + tmpGuardXVelocity][tmpGuardY + tmpGuardYVelocity];
            } catch (ArrayIndexOutOfBoundsException e) {
                //Looking off the map means no obstruction
            }
            if (isObstructionInFront) {
                //Turn if obstructed
                if (tmpGuardXVelocity == -1) {
                    tmpGuardXVelocity = 0;
                    tmpGuardYVelocity = 1;
                } else if (tmpGuardXVelocity == 1) {
                    tmpGuardXVelocity = 0;
                    tmpGuardYVelocity = -1;
                } else if (tmpGuardYVelocity == -1) {
                    tmpGuardXVelocity = -1;
                    tmpGuardYVelocity = 0;
                } else if (tmpGuardYVelocity == 1) {
                    tmpGuardXVelocity = 1;
                    tmpGuardYVelocity = 0;
                }
            } else {
                //Else move one position
                tmpGuardX += tmpGuardXVelocity;
                tmpGuardY += tmpGuardYVelocity;
            }

            stepCount++;
        }
        //Exited map so not in loop
    }

    private void turn() {
        if (guardXVelocity == -1) {
            guardXVelocity = 0;
            guardYVelocity = 1;
        } else if (guardXVelocity == 1) {
            guardXVelocity = 0;
            guardYVelocity = -1;
        } else if (guardYVelocity == -1) {
            guardXVelocity = -1;
            guardYVelocity = 0;
        } else if (guardYVelocity == 1) {
            guardXVelocity = 1;
            guardYVelocity = 0;
        }
    }

    private record GuardPosition(int x, int y) {
    }
}
