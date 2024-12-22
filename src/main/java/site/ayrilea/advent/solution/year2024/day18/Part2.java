package site.ayrilea.advent.solution.year2024.day18;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

@SolutionMetadata(year = 2024, day = 18, part = 2)
public class Part2 implements Solution<String> {

    @Override
    public String solve(Input input) {
        Solver solver = new Solver(input);
        for (int bytes = solver.getBytesReference() + 1; bytes <= solver.getTotalBytes(); bytes++) {
            if (solver.getShortestPath(bytes) == -1) {
                return solver.getByte(bytes - 1).toString();
            }
        }
        throw new IllegalStateException("No first byte blocking path found");
    }
}