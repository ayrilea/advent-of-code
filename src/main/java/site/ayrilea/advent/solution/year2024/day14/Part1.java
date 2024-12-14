package site.ayrilea.advent.solution.year2024.day14;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;
import static site.ayrilea.advent.solution.year2024.day14.Shared.parseRobots;

@SolutionMetadata(year = 2024, day = 14, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part1::processRobots);
    }

    private static Long processRobots(Stream<Robot> robots) {
        return robots.peek(robot -> IntStream.range(0, 100)
                        .forEach(_ -> robot.simulate()))
                .collect(groupingBy(Robot::getQuadrant, counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != 0) //Exclude robots between quadrants
                .map(Map.Entry::getValue)
                .reduce(1L, (a, b) -> a * b);
    }
}