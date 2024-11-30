package site.ayrilea.advent.driver;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;

public class Solver {

    public static <T> Result<T> solve(Solution<T> solution, Input input) {
        T value = solution.solve(input);
        return new Result<>(solution, value);
    }
}
