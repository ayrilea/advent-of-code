package site.ayrilea.advent.solution.year2024.day7;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;

import static site.ayrilea.advent.solution.year2024.day7.Operand.*;

@SolutionMetadata(year = 2024, day = 7, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return input.stream()
                .map(Equation::fromInput)
                .filter(equation -> equation.isPossible(List.of(ADDITION, CONCATENATION, MULTIPLICATION)))
                .map(Equation::result)
                .mapToLong(l -> l)
                .sum();
    }
}