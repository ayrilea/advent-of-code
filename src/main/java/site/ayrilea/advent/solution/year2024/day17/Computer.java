package site.ayrilea.advent.solution.year2024.day17;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Computer {

    private final List<Instruction> instructions;
    private final List<Long> output;

    private int instructionPointer;
    private long registerA;
    private long registerB;
    private long registerC;

    private Computer(List<Instruction> instructions) {
        this.instructions = instructions;

        instructionPointer = 0;
        output = new LinkedList<>();
    }

    private Computer(Builder builder) {
        instructions = List.copyOf(builder.instructions);
        registerA = builder.registerA;
        registerB = builder.registerB;
        registerC = builder.registerC;

        instructionPointer = 0;
        output = new LinkedList<>();
    }

    String getOutput() {
        return output.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    long getRegisterA() {
        return registerA;
    }

    long getRegisterB() {
        return registerB;
    }

    long getRegisterC() {
        return registerC;
    }

    void output(long value) {
        output.add(value);
    }

    void run() {
        while (instructionPointer < instructions.size()) {
            step();
        }
    }

    void setInstructionPointer(int value) {
        instructionPointer = value;
    }

    void setRegisterA(long value) {
        registerA = value;
    }

    void setRegisterB(long value) {
        registerB = value;
    }

    void setRegisterC(long value) {
        registerC = value;
    }

    private void step() {
        instructions.get(instructionPointer).apply(this);
        instructionPointer++;
    }

    static Computer forInstructions(List<Instruction> instructions) {
        return new Computer(instructions);
    }

    static class Builder {

        private final List<Instruction> instructions;

        private long registerA;
        private long registerB;
        private long registerC;

        Builder() {
            this.instructions = new LinkedList<>();
        }

        Computer build() {
            return new Computer(this);
        }

        Builder instruction(Instruction value) {
            instructions.add(value);
            return this;
        }

        Builder registerA(long value) {
            registerA = value;
            return this;
        }

        Builder registerB(long value) {
            registerB = value;
            return this;
        }

        Builder registerC(long value) {
            registerC = value;
            return this;
        }
    }
}
