package site.ayrilea.advent.solution.year2024.day12;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@SolutionMetadata(year = 2024, day = 12, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        return Shared.solve(input, region -> region.getArea() * sidesForRegion(region));
    }

    private static int numberOfHorizontalSides(List<Position> positions) {
        List<Integer> values = positions.stream()
                .sorted(Comparator.comparingInt(Position::column))
                .map(Position::column)
                .toList();
        int sides = 1;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) != values.get(i + 1) - 1) {
                sides++;
            }
        }
        return sides;
    }

    private static int numberOfVerticalSides(List<Position> positions) {
        List<Integer> values = positions.stream()
                .sorted(Comparator.comparingInt(Position::row))
                .map(Position::row)
                .toList();
        int sides = 1;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) != values.get(i + 1) - 1) {
                sides++;
            }
        }
        return sides;
    }

    private static int sidesForRegion(Region region) {
        Set<Position> positions = region.getPositions();

        List<Position> down = new LinkedList<>();
        List<Position> left = new LinkedList<>();
        List<Position> right = new LinkedList<>();
        List<Position> up = new LinkedList<>();

        for (Position position : positions) {
            int row = position.row();
            int column = position.column();
            if (!positions.contains(new Position(row - 1, column))) {
                up.add(position);
            }
            if (!positions.contains(new Position(row + 1, column))) {
                down.add(position);
            }
            if (!positions.contains(new Position(row, column - 1))) {
                left.add(position);
            }
            if (!positions.contains(new Position(row, column + 1))) {
                right.add(position);
            }
        }

        int downSides = down.stream()
                .collect(groupingBy(Position::row, toList()))
                .values()
                .stream()
                .map(Part2::numberOfHorizontalSides)
                .mapToInt(i -> i)
                .sum();

        int leftSides = left.stream()
                .collect(groupingBy(Position::column, toList()))
                .values()
                .stream()
                .map(Part2::numberOfVerticalSides)
                .mapToInt(i -> i)
                .sum();


        int rightSides = right.stream()
                .collect(groupingBy(Position::column, toList()))
                .values()
                .stream()
                .map(Part2::numberOfVerticalSides)
                .mapToInt(i -> i)
                .sum();

        int upSides = up.stream()
                .collect(groupingBy(Position::row, toList()))
                .values()
                .stream()
                .map(Part2::numberOfHorizontalSides)
                .mapToInt(i -> i)
                .sum();

        return downSides + leftSides + rightSides + upSides;
    }
}