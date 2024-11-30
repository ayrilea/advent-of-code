package site.ayrilea.advent.solution;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SolutionMetadata {

    int year();

    int day();

    int part();
}
