package site.ayrilea.advent.solution.year2024.day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Robot {

    private static final Pattern PATTERN_ROBOT = Pattern.compile(
            "p=(?<xPosition>[-\\d]+),(?<yPosition>[-\\d]+) v=(?<xVelocity>[-\\d]+),(?<yVelocity>[-\\d]+)");

    private final int xMax;
    private final int xVelocity;
    private final int yMax;
    private final int yVelocity;

    private int xPosition;
    private int yPosition;

    private Robot(int xMax, int xPosition, int xVelocity, int yMax, int yPosition, int yVelocity) {
        this.xMax = xMax;
        this.xPosition = xPosition;
        this.xVelocity = xVelocity;
        this.yMax = yMax;
        this.yPosition = yPosition;
        this.yVelocity = yVelocity;
    }

    static Robot fromLine(String line, int xMax, int yMax) {
        Matcher matcher = PATTERN_ROBOT.matcher(line);
        if (matcher.matches()) {
            int xPosition = Integer.parseInt(matcher.group("xPosition"));
            int xVelocity = Integer.parseInt(matcher.group("xVelocity"));
            int yPosition = Integer.parseInt(matcher.group("yPosition"));
            int yVelocity = Integer.parseInt(matcher.group("yVelocity"));
            return new Robot(xMax, xPosition, xVelocity, yMax, yPosition, yVelocity);
        }
        throw new IllegalArgumentException("Invalid input line: " + line);
    }

    Position getPosition() {
        return new Position(xPosition, yPosition);
    }

    int getQuadrant() {
        int xMiddle = xMax / 2;
        int yMiddle = yMax / 2;

        //Middle row or column
        if (xPosition == xMiddle || yPosition == yMiddle) {
            return 0;
        }

        //Top Left
        if (xPosition < xMiddle && yPosition < yMiddle) {
            return 1;
        }
        //Top Right
        if (xPosition > xMiddle && yPosition < yMiddle) {
            return 2;
        }
        //Bottom Left
        if (xPosition < xMiddle && yPosition > yMiddle) {
            return 3;
        }
        //Bottom Right
        if (xPosition > xMiddle && yPosition > yMiddle) {
            return 4;
        }

        throw new IllegalStateException("Invalid quadrant");
    }

    void simulate() {
        xPosition += xVelocity;
        if (xPosition > xMax - 1) {
            xPosition -= xMax;
        }
        if (xPosition < 0) {
            xPosition += xMax;
        }
        yPosition += yVelocity;
        if (yPosition > yMax - 1) {
            yPosition -= yMax;
        }
        if (yPosition < 0) {
            yPosition += yMax;
        }
    }
}
