package site.ayrilea.advent.solution.year2024.day7;

import java.util.function.BiFunction;

enum Operand {

    ADDITION(Long::sum),
    CONCATENATION((first, second) -> Long.parseLong(String.valueOf(first) + second)),
    MULTIPLICATION((first, second) -> first * second);

    private final BiFunction<Long, Long, Long> operation;

    Operand(BiFunction<Long, Long, Long> operation) {
        this.operation = operation;
    }

    long apply(long first, long second) {
        return operation.apply(first, second);
    }
}
