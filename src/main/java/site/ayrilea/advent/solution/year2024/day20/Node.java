package site.ayrilea.advent.solution.year2024.day20;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Node implements Comparable<Node> {

    private final int length;
    private final List<Position> path;
    private final Position position;

    Node(int length, Position position) {
        this.length = length;
        this.path = Collections.emptyList();
        this.position = position;
    }

    private Node(int length, List<Position> path, Position position) {
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
        List<Position> path = new LinkedList<>(previous.getPath());
        path.add(position);
        return new Node(length, path, position);
    }

    static Node fromNode(Node previous, Position position, int extraLength) {
        int length = previous.getLength() + extraLength;
        List<Position> path = new LinkedList<>(previous.getPath());
        path.add(position);
        return new Node(length, path, position);
    }

    static Node initialNode(Position position) {
        return new Node(0, List.of(position), position);
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
}
