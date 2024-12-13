package site.ayrilea.advent.solution;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import site.ayrilea.advent.driver.SolutionArguments;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

public class SolutionRetriever {

    private static final Reflections reflections = new Reflections(
            new ConfigurationBuilder()
                    .forPackage("site.ayrilea.advent"));

    public static Solution<?> solutionFor(SolutionArguments arguments) {
        Set<Class<?>> solutions = reflections.get(TypesAnnotated.with(SolutionMetadata.class).asClass());
        return solutions.stream()
                .filter(Solution.class::isAssignableFrom)
                .filter(solution -> {
                    SolutionMetadata metadata = solution.getAnnotation(SolutionMetadata.class);
                    return arguments.getYear() == metadata.year() &&
                            arguments.getDay() == metadata.day() &&
                            arguments.getPart() == metadata.part();
                })
                .map(aClass -> {
                    try {
                        return (Solution<?>) aClass.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException("Unable to instantiate class");
                    }
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchSolutionException("Solution for [" + arguments + "] does not exist"));
    }
}
