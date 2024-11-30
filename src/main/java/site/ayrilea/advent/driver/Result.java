package site.ayrilea.advent.driver;

import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

public class Result<T> {

    private static final String RESULT_FORMAT = "Year %d, Day %d, Part %d\nSolution: %s\n";

    private final SolutionMetadata metadata;
    private final T value;

    public Result(Solution<T> solution, T value) {
        this.metadata = solution.getClass().getAnnotationsByType(SolutionMetadata.class)[0];
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return RESULT_FORMAT.formatted(metadata.year(), metadata.day(), metadata.part(), value);
    }
}
