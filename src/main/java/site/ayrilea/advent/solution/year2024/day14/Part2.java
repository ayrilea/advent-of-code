package site.ayrilea.advent.solution.year2024.day14;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@SolutionMetadata(year = 2024, day = 14, part = 2)
public class Part2 implements Solution<Long> {

    private static final int MIN_CONSECUTIVE_IN_ROW_FOR_TREE = 30;

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part2::processRobots);
    }

    private static boolean couldBeChristmasTree(Set<Position> positions) {
        return positions.stream()
                .collect(groupingBy(Position::y, mapping(Position::x, toList())))
                .values()
                .stream()
                .anyMatch(columns -> hasConsecutiveElements(columns));
    }

    private static Set<Position> getRobotPositions(List<Robot> robots) {
        return robots.stream()
                .map(Robot::getPosition)
                .collect(Collectors.toUnmodifiableSet());
    }

    private static boolean hasConsecutiveElements(List<Integer> numbers) {
        List<Integer> sorted = numbers.stream().sorted().toList();

        int numConsecutive = 0;
        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i) == sorted.get(i + 1) - 1) {
                numConsecutive++;
            } else {
                numConsecutive = 0;
            }
            if (numConsecutive >= MIN_CONSECUTIVE_IN_ROW_FOR_TREE) {
                return true;
            }
        }
        return false;
    }

    private static void printPositions(Set<Position> positions, Bounds bounds) {
        for (int y = 0; y < bounds.yMax(); y++) {
            for (int x = 0; x < bounds.xMax(); x++) {
                if (positions.contains(new Position(x, y))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private static Long processRobots(Stream<Robot> robotStream, Bounds bounds) {
        List<Robot> robots = robotStream.toList();

        for (int t = 1; t <= bounds.xMax() * bounds.yMax(); t++) {
            robots.forEach(Robot::simulate);
            Set<Position> positions = getRobotPositions(robots);

            if (couldBeChristmasTree(positions)) {
                System.out.println("Seconds: " + t);
                printPositions(positions, bounds);
                System.out.println();
                return (long) t;
            }
        }
        System.out.println("No iteration has a Christmas tree");
        return 0L;
    }
}