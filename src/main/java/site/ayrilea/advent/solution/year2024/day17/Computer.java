package site.ayrilea.advent.solution.year2024.day17;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Computer {

    private final List<Instruction> instructions;
    private final List<Integer> output;

    private int instructionPointer;
    private int registerA;
    private int registerB;
    private int registerC;

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

    int getRegisterA() {
        return registerA;
    }

    int getRegisterB() {
        return registerB;
    }

    int getRegisterC() {
        return registerC;
    }

    void output(int value) {
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

    void setRegisterA(int value) {
        registerA = value;
    }

    void setRegisterB(int value) {
        registerB = value;
    }

    void setRegisterC(int value) {
        registerC = value;
    }

    private void step() {
        instructions.get(instructionPointer).apply(this);
        instructionPointer++;
    }

    static class Builder {

        private final List<Instruction> instructions;

        private int registerA;
        private int registerB;
        private int registerC;

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

        Builder registerA(int value) {
            registerA = value;
            return this;
        }

        Builder registerB(int value) {
            registerB = value;
            return this;
        }

        Builder registerC(int value) {
            registerC = value;
            return this;
        }
    }
}
