package site.ayrilea.advent.solution.year2025.day1;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;

@SolutionMetadata(year = 2025, day = 1, part = 2)
public class Part2 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        int count = 0;
        List<Integer> rotations = input.stream().map(Shared::parseLine).toList();

        int value = 50;
        System.out.println("Value=[" + value + "]  Count=[" + count + "]");
        for (int rotation : rotations) {
            int originalValue = value;
            int fullRotations = rotation / 100;
            int netRotation = rotation % 100;

            value += netRotation;
            if (value == 100) {
                value = 0;
            }
            if (value > 99) {
                value -= 100;
                count++;
            }
            if (value < 0) {
                value += 100;
                if (originalValue != 0) {
                    count++;
                }
            }

            if (value == 0) {
                count++;
            }
            count += Math.abs(fullRotations);

            System.out.println("Value=[" + value + "]  Count=[" + count + "]");
        }

        return count;
    }
}