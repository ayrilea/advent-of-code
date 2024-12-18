package site.ayrilea.advent.solution.year2024.day17;

import java.util.function.BiConsumer;
import java.util.function.Function;

enum Operator {

    ADV(0, (computer, operand) -> {
        long numerator = computer.getRegisterA();
        int denominator = (int) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterA(numerator / denominator);
    },  operand -> "ADV " + labelComboOperand(operand)),
    BDV(6, (computer, operand) -> {
        long numerator = computer.getRegisterA();
        int denominator = (int) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterB(numerator / denominator);
    }, operand -> "BDV " + labelComboOperand(operand)),
    BXC(4, (computer, _) -> {
        long value = computer.getRegisterB() ^ computer.getRegisterC();
        computer.setRegisterB(value);
    }, _ -> "BXC"),
    BXL(1, (computer, operand) -> {
        long value = computer.getRegisterB() ^ operand;
        computer.setRegisterB(value);
    }, operand -> "BXL " + operand),
    BST(2, (computer, operand) -> {
        long value = parseComboOperand(computer, operand) % 8;
        computer.setRegisterB(value);
    }, operand -> "BST " + labelComboOperand(operand)),
    CDV(7, (computer, operand) -> {
        long numerator = computer.getRegisterA();
        long denominator = (long) Math.pow(2, parseComboOperand(computer, operand));
        computer.setRegisterC(numerator / denominator);
    }, operand -> "CDV " + labelComboOperand(operand)),
    JNZ(3, (computer, operand) -> {
        if (computer.getRegisterA() != 0) {
            computer.setInstructionPointer(operand - 1); //Computer will always increment by one
        }
    }, operand -> "JNZ " + operand),
    OUT(5, (computer, operand) -> {
        long output = parseComboOperand(computer, operand) % 8;
        computer.output(output);
    }, operand -> "OUT " + labelComboOperand(operand));

    private final Function<Integer, String> label;
    private final int opcode;
    private final BiConsumer<Computer, Integer> operation;

    Operator(int opcode, BiConsumer<Computer, Integer> operation, Function<Integer, String> label) {
        this.label = label;
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

    String label(int operand) {
        return label.apply(operand);
    }

    private static String labelComboOperand(int operand) {
        if (operand == 4) {
            return "A";
        }
        if (operand == 5) {
            return "B";
        }
        if (operand == 6) {
            return "C";
        }
        if (operand == 7) {
            throw new IllegalStateException("Illegal combo operand: " + operand);
        }
        return String.valueOf(operand);
    }

    private static long parseComboOperand(Computer computer, int operand) {
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
