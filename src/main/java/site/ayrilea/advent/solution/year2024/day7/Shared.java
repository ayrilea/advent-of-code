package site.ayrilea.advent.solution.year2024.day7;

import site.ayrilea.advent.input.Input;

import java.util.List;

class Shared {

    static long solveForOperands(Input input, List<Operand> operands) {
        return input.stream()
                .map(Equation::fromInput)
                .filter(equation -> equation.isPossible(operands))
                .map(Equation::result)
                .mapToLong(l -> l)
                .sum();
    }
}
