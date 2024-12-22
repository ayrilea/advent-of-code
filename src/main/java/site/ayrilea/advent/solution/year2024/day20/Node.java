package site.ayrilea.advent.solution.year2024.day20;

import java.util.Objects;

class Node implements Comparable<Node> {

    private final int length;
    private final Position position;

    private Node(int length, Position position) {
        this.length = length;
        this.position = position;
    }

    @Override
    public int compareTo(Node o) {
        return length - o.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Node n) {
            return Objects.equals(position, n.position);
        }
        return false;
    }

    static Node fromNode(Node previous, Position position) {
        int length = previous.getLength() + 1;
        return new Node(length, position);
    }

    static Node fromNode(Node previous, Position position, int extraLength) {
        int length = previous.getLength() + extraLength;
        return new Node(length, position);
    }

    static Node initialNode(Position position) {
        return new Node(0, position);
    }

    int getLength() {
        return length;
    }

    Position getPosition() {
        return position;
    }
}
