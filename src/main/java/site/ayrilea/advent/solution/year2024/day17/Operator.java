package site.ayrilea.advent.solution.year2024.day17;

import java.util.function.BiConsumer;

enum Operator {

    ADV(0, (computer, operand) -> {
        int numerator = computer.getRegisterA();
        int denominator = (int) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterA(numerator / denominator);
    }),
    BDV(6, (computer, operand) -> {
        int numerator = computer.getRegisterA();
        int denominator = (int) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterB(numerator / denominator);
    }),
    BXC(4, (computer, _) -> {
        int value = computer.getRegisterB() ^ computer.getRegisterC();
        computer.setRegisterB(value);
    }),
    BXL(1, (computer, operand) -> {
        int value = computer.getRegisterB() ^ operand;
        computer.setRegisterB(value);
    }),
    BST(2, (computer, operand) -> {
        int value = parseComboOperand(computer, operand) % 8;
        computer.setRegisterB(value);
    }),
    CDV(7, (computer, operand) -> {
        int numerator = computer.getRegisterA();
        int denominator = (int) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterC(numerator / denominator);
    }),
    JNZ(3, (computer, operand) -> {
        if (computer.getRegisterA() != 0) {
            computer.setInstructionPointer(operand - 1); //Computer will always increment by one
        }
    }),
    OUT(5, (computer, operand) -> {
        int output = parseComboOperand(computer, operand) % 8;
        computer.output(output);
    });

    private final int opcode;
    private final BiConsumer<Computer, Integer> operation;

    Operator(int opcode, BiConsumer<Computer, Integer> operation) {
        this.opcode = opcode;
        this.operation = operation;
    }

    static Operator fromOpcode(int opcode) {
        for (Operator operator : Operator.values()) {
            if (opcode == operator.opcode) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Invalid opcode: " + opcode);
    }

    void apply(Computer computer, int operand) {
        operation.accept(computer, operand);
    }

    private static int parseComboOperand(Computer computer, int operand) {
        if (operand == 4) {
            return computer.getRegisterA();
        }
        if (operand == 5) {
            return computer.getRegisterB();
        }
        if (operand == 6) {
            return computer.getRegisterC();
        }
        if (operand == 7) {
            throw new IllegalStateException("Illegal combo operand: " + operand);
        }
        return operand;
    }
}
