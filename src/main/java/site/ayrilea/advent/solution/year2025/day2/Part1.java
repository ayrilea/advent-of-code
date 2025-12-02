package site.ayrilea.advent.solution.year2025.day2;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Objects;

@SolutionMetadata(year = 2025, day = 2, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part1::isInvalid);
    }

    private static boolean isInvalid(long id) {
        String label = Long.toString(id);

        //Odd length IDs are always valid
        if (label.length() % 2 == 1) {
            return false;
        }

        String firstHalf = label.substring(0, label.length() / 2);
        String secondHalf = label.substring(label.length() / 2);

        return Objects.equals(firstHalf, secondHalf);
    }
}