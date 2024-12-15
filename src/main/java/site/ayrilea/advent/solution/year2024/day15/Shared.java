package site.ayrilea.advent.solution.year2024.day15;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.List;

import static site.ayrilea.advent.solution.year2024.day15.Obstacle.*;

class Shared {

    static int solve(Input input) {
        List<String> lines = input.list();
        int emptyLine = lines.indexOf("");

        List<String> mapLines = lines.subList(0, emptyLine);
        String movesString = String.join("", lines.subList(emptyLine + 1, lines.size()));

        Obstacle[][] map = parseMap(mapLines);
        List<Move> moves = parseMoves(movesString);
        Robot robot = parseRobot(mapLines);

        moves.forEach(move -> processMove(move, map, robot));

        return calculateGpsScoreSum(map);
    }

    private static int calculateGpsScoreSum(Obstacle[][] map) {
        int gpsScoreSum = 0;
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == BOULDER) {
                    gpsScoreSum += (100 * row + column);
                }
            }
        }
        return gpsScoreSum;
    }

    private static Obstacle[][] parseMap(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(""))
                .map(parts -> Arrays.stream(parts)
                        .map(label -> "@".equals(label) ? "." : label)
                        .map(Obstacle::fromLabel)
                        .toArray(Obstacle[]::new))
                .toArray(Obstacle[][]::new);
    }

    private static List<Move> parseMoves(String line) {
        return Arrays.stream(line.split(""))
                .map(Move::fromLabel)
                .toList();
    }

    private static Robot parseRobot(List<String> lines) {
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.getFirst().length(); column++) {
                if ('@' == lines.get(row).charAt(column)) {
                    return new Robot(column, row);
                }
            }
        }
        throw new IllegalStateException("No robot in map");
    }

    private static void processMove(Move move, Obstacle[][] map, Robot robot) {
        int robotColumn = robot.getColumn();
        int robotRow = robot.getRow();

        switch (move) {
            case DOWN -> {
                Obstacle below = map[robotRow + 1][robotColumn];
                if (below == NONE) {
                    robot.move(move);
                }
                if (below == BOULDER) {
                    int bouldersToMove = 1;
                    boolean shouldMove = false;
                    for (int row = robotRow + 2; row < map.length; row++) {
                        Obstacle obstacle = map[row][robotColumn];
                        if (obstacle == WALL) {
                            break;
                        }
                        if (obstacle == NONE) {
                            shouldMove = true;
                            break;
                        }
                        if (obstacle == BOULDER) {
                            bouldersToMove++;
                        }
                    }
                    if (shouldMove) {
                        robot.move(move);
                        map[robotRow + 1][robotColumn] = NONE;
                        map[robotRow + bouldersToMove + 1][robotColumn] = BOULDER;
                    }
                }
            }
            case LEFT -> {
                Obstacle left = map[robotRow][robotColumn - 1];
                if (left == NONE) {
                    robot.move(move);
                }
                if (left == BOULDER) {
                    int bouldersToMove = 1;
                    boolean shouldMove = false;
                    for (int column = robotColumn - 2; column >= 0; column--) {
                        Obstacle obstacle = map[robotRow][column];
                        if (obstacle == WALL) {
                            break;
                        }
                        if (obstacle == NONE) {
                            shouldMove = true;
                            break;
                        }
                        if (obstacle == BOULDER) {
                            bouldersToMove++;
                        }
                    }
                    if (shouldMove) {
                        robot.move(move);
                        map[robotRow][robotColumn - 1] = NONE;
                        map[robotRow][robotColumn - (bouldersToMove + 1)] = BOULDER;
                    }
                }
            }
            case RIGHT -> {
                Obstacle right = map[robotRow][robotColumn + 1];
                if (right == NONE) {
                    robot.move(move);
                }
                if (right == BOULDER) {
                    int bouldersToMove = 1;
                    boolean shouldMove = false;
                    for (int column = robotColumn + 2; column < map[0].length; column++) {
                        Obstacle obstacle = map[robotRow][column];
                        if (obstacle == WALL) {
                            break;
                        }
                        if (obstacle == NONE) {
                            shouldMove = true;
                            break;
                        }
                        if (obstacle == BOULDER) {
                            bouldersToMove++;
                        }
                    }
                    if (shouldMove) {
                        robot.move(move);
                        map[robotRow][robotColumn + 1] = NONE;
                        map[robotRow][robotColumn + bouldersToMove + 1] = BOULDER;
                    }
                }
            }
            case UP -> {
                Obstacle up = map[robotRow - 1][robotColumn];
                if (up == NONE) {
                    robot.move(move);
                }
                if (up == BOULDER) {
                    int bouldersToMove = 1;
                    boolean shouldMove = false;
                    for (int row = robotRow - 2; row >= 0; row--) {
                        Obstacle obstacle = map[row][robotColumn];
                        if (obstacle == WALL) {
                            break;
                        }
                        if (obstacle == NONE) {
                            shouldMove = true;
                            break;
                        }
                        if (obstacle == BOULDER) {
                            bouldersToMove++;
                        }
                    }
                    if (shouldMove) {
                        robot.move(move);
                        map[robotRow - 1][robotColumn] = NONE;
                        map[robotRow - (bouldersToMove + 1)][robotColumn] = BOULDER;
                    }
                }
            }
        }
    }
}
