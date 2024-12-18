package site.ayrilea.advent.solution.year2024.day17;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Gatherers.windowFixed;

class Shared {

    private static final Pattern PATTERN_PROGRAM = Pattern.compile("Program: (?<program>.*)");
    private static final Pattern PATTERN_REGISTER = Pattern.compile("Register [ABC]: (?<value>\\d+)");

    static Computer parseInput(List<String> lines) {
        Computer.Builder builder = new Computer.Builder();

        parseRegister(lines.getFirst(), builder::registerA);
        parseRegister(lines.get(1), builder::registerB);
        parseRegister(lines.get(2), builder::registerC);
        parseInstructions(lines.getLast(), builder::instruction);

        return builder.build();
    }

    static String extractProgram(String line) {
        Matcher matcher = PATTERN_PROGRAM.matcher(line);
        if (matcher.matches()) {
            return matcher.group("program");
        }
        throw new IllegalArgumentException("Invalid line: " + line);
    }

    static Stream<Instruction> mapToInstructions(String program) {
        return Arrays.stream(program.split(","))
                .map(Integer::parseInt)
                .gather(windowFixed(2))
                .map(l -> new Instruction(Operator.fromOpcode(l.getFirst()), l.getLast()));
    }

    private static void parseInstructions(String line, Consumer<Instruction> builder) {
        mapToInstructions(extractProgram(line)).forEachOrdered(builder);
    }

    private static void parseRegister(String line, Consumer<Integer> builder) {
        Matcher matcher = PATTERN_REGISTER.matcher(line);
        if (matcher.matches()) {
            builder.accept(Integer.parseInt(matcher.group("value")));
        } else {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
    }
}
