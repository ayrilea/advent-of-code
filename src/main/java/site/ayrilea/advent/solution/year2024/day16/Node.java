package site.ayrilea.advent.solution.year2024.day16;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static site.ayrilea.advent.solution.year2024.day16.Direction.NORTH;

class Node implements Comparable<Node> {

    private final Direction direction;
    private final List<Position> path;
    private final Position position;

    private int length;

    Node(Position position, Direction direction) {
        this.direction = direction;
        this.position = position;

        length = 0;
        path = new LinkedList<>();
        path.add(position);
    }

    Node(Position position, Direction direction, int length) {
        this.direction = direction;
        this.length = length;
        this.position = position;

        path = new LinkedList<>();
    }

    private Node(Builder builder) {
        direction = builder.direction;
        length = builder.length;
        path = List.copyOf(builder.path);
        position = builder.position;
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

    static class Builder {

        private final Direction direction;
        private final List<Position> path;
        private final Position position;

        private int length;

        Builder(Position position, Direction direction) {
            this.direction = direction;
            this.position = position;

            path = new LinkedList<>();
            length = 0;
        }

        Node build() {
            return new Node(this);
        }

        Builder lengthFrom(Node previous) {
            length = previous.getLength() + calculateTurningCost(previous.getDirection()) + 1;

            return this;
        }

        private int calculateTurningCost(Direction previous) {
            if (direction == previous) {
                return 0;
            }
            return 1000;
        }
    }
}
