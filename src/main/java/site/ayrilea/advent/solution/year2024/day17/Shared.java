package site.ayrilea.advent.solution.year2024.day17;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Gatherers.windowFixed;

class Shared {

    private static final Pattern PATTERN_PROGRAM = Pattern.compile("Program: (?<program>.*)");
    private static final Pattern PATTERN_REGISTER = Pattern.compile("Register [ABC]: (?<value>\\d+)");

    static Computer parseInput(Input input) {
        List<String> lines = input.list();
        Computer.Builder builder = new Computer.Builder();

        parseRegister(lines.getFirst(), builder::registerA);
        parseRegister(lines.get(1), builder::registerB);
        parseRegister(lines.get(2), builder::registerC);
        parseInstructions(lines.getLast(), builder::instruction);

        return builder.build();
    }

    private static void parseInstructions(String line, Consumer<Instruction> builder) {
        Matcher matcher = PATTERN_PROGRAM.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }

        Arrays.stream(matcher.group("program").split(","))
                .map(Integer::parseInt)
                .gather(windowFixed(2))
                .map(l -> new Instruction(Operator.fromOpcode(l.getFirst()), l.getLast()))
                .forEachOrdered(builder);
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
