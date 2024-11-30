package site.ayrilea.advent.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ayrilea.advent.driver.Result;
import site.ayrilea.advent.driver.Solver;
import site.ayrilea.advent.input.FileInput;
import site.ayrilea.advent.input.Input;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractSolutionTest<T> {

    protected Solution<T> solution;

    @BeforeEach
    public void beforeEach() {
        solution = createSolution();
    }

    @Test
    public void exampleInput() {
        SolutionMetadata metadata = solution.getClass().getAnnotation(SolutionMetadata.class);
        Input exampleInput = FileInput.inputFor(metadata);

        assertSolution(exampleExpectedValue(), exampleInput);
    }

    protected void assertSolution(T expectedValue, Input input) {
        Result<T> result = Solver.solve(solution, input);
        assertEquals(expectedValue, result.getValue());
    }

    protected abstract Solution<T> createSolution();

    protected abstract T exampleExpectedValue();
}
