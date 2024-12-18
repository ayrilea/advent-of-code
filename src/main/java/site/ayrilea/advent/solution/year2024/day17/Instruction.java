package site.ayrilea.advent.solution.year2024.day17;

record Instruction(Operator operator, int operand) {

    void apply(Computer computer) {
        operator.apply(computer, operand);
    }

    @Override
    public String toString() {
        return operator.label(operand);
    }
}
