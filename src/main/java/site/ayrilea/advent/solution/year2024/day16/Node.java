package site.ayrilea.advent.solution.year2024.day16;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Node implements Comparable<Node> {

    private final Direction direction;
    private final int length;
    private final List<Position> path;
    private final Position position;

    private Node(Direction direction, int length, List<Position> path, Position position) {
        this.direction = direction;
        this.length = length;
        this.path = path;
        this.position = position;
    }

    @Override
    public int compareTo(Node o) {
        return length - o.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, position);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Node n) {
            return Objects.equals(direction, n.direction) && Objects.equals(position, n.position);
        }
        return false;
    }

    static Node fromNode(Node previous, Position position, Direction direction) {
        int length = previous.getLength() + calculateTurningCost(previous.getDirection(), direction) + 1;
        return new Node(direction, length, new LinkedList<>(), position);
    }

    static Node initialNode(Position position, Direction direction) {
        return new Node(direction, 0, new LinkedList<>(), position);
    }

    Direction getDirection() {
        return direction;
    }

    int getLength() {
        return length;
    }

    List<Position> getPath() {
        return path;
    }

    Position getPosition() {
        return position;
    }

    private static int calculateTurningCost(Direction previous, Direction current) {
        if (current == previous) {
            return 0;
        }
        return 1000;
    }
}
