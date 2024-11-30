package site.ayrilea.advent.driver;

import site.ayrilea.advent.input.FileInput;
import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionRetriever;

import static site.ayrilea.advent.driver.SolutionArguments.parseArgs;

public class SolutionDriver {

    public static void main(String[] args) {
        SolutionArguments arguments = parseArgs(args);

        Input input = FileInput.inputFor(arguments);
        Solution<?> solution = SolutionRetriever.solutionFor(arguments);
        Result<?> result = Solver.solve(solution, input);

        System.out.println(result);
    }
}
