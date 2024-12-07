package site.ayrilea.advent.solution.year2024.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Equation(long result, List<Long> numbers) {

    static Equation fromInput(String line) {
        String[] parts = line.split(" ");

        long result = Long.parseLong(parts[0].substring(0, parts[0].length() - 1));
        List<Long> numbers = Arrays.stream(parts, 1, parts.length)
                .map(Long::parseLong)
                .toList();

        return new Equation(result, numbers);
    }

    boolean isPossible(List<Operand> possibleOperands) {
        int numOperands = possibleOperands.size();
        double numPermutations = Math.pow(numOperands, numbers.size() - 1);

        for (int permutation = 0; permutation < numPermutations; permutation++) {
            List<Operand> operators = new ArrayList<>(numbers.size() - 1);
            for (int operator = 0; operator < numbers.size() - 1; operator++) {
                int selectedOperandIndex = (int) (permutation / Math.pow(numOperands, operator)) % numOperands;
                operators.add(operator, possibleOperands.get(selectedOperandIndex));
            }
            long result = numbers.getFirst();
            for (int numberIndex = 1; numberIndex < numbers.size(); numberIndex++) {
                long current = numbers.get(numberIndex);
                Operand operator = operators.get(numberIndex - 1);
                result = operator.apply(result, current);
            }
            if (this.result == result) {
                return true;
            }
        }
        return false;
    }

}
