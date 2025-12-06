package site.ayrilea.advent.solution.year2025.day6;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SolutionMetadata(year = 2025, day = 6, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        List<String> lines = input.list();

        int numberOfOperands = lines.size() - 1;
        List<List<String>> operandGroups = new ArrayList<>(numberOfOperands);
        for (int i = 0; i < numberOfOperands; i++) {
            operandGroups.add(i, new ArrayList<>());
        }
        List<String> operations = new ArrayList<>();

        //Parse operands with space padding preserved by basing it off the _operations_ input line. This input line
        //always has the operation in the left-most aligned column for the given puzzle. This means the number of spaces
        //between operations sets the bounds for reading a given problem's operands.
        String operationsLine = lines.getLast();
        int index = 0;
        while (index < operationsLine.length()) {
            //Read next operation
            int startIndex = index;
            String operation = String.valueOf(operationsLine.charAt(index));
            operations.add(operation);

            index++;
            if (index < operationsLine.length()) {
                //Find number of padding spaces after operation
                String current = String.valueOf(operationsLine.charAt(index));
                while (Objects.equals(" ", current)) {
                    index++;
                    current = String.valueOf(operationsLine.charAt(index));
                }

                //Reading operands starting at operation index up to one before the end of padding (which accounts for
                //the shared space between problems).
                for (int i = 0; i < numberOfOperands; i++) {
                    String operand = lines.get(i).substring(startIndex, index - 1);
                    operandGroups.get(i).add(operand);
                }
            } else {
                //Final operand has no space padding after it
                for (int i = 0; i < numberOfOperands; i++) {
                    String operand = lines.get(i).substring(startIndex);
                    operandGroups.get(i).add(operand);
                }
            }
        }

        long sum = 0L;
        for (int i = 0; i < operations.size(); i++) {
            //Get the operands for the current problem (still in padded string form).
            List<String> operands = new ArrayList<>();
            for (int j = 0; j < numberOfOperands; j++) {
                operands.add(operandGroups.get(j).get(i));
            }

            //Convert padded string form until column read numbers, starting rightmost.
            List<Long> longs = new ArrayList<>();
            int numberOfLongs = operands.stream()
                    .map(String::length)
                    .mapToInt(o -> o)
                    .max()
                    .orElseThrow();
            for (int j = numberOfLongs - 1; j >= 0; j--) {
                int charIndex = j;
                String longVal = operands.stream()
                        .map(o -> {
                            if (charIndex < o.length()) {
                                return o.charAt(charIndex);
                            }
                            //Handling for final puzzle which lacks trailing zeros
                            return " ";
                        })
                        .map(String::valueOf)
                        //Just ignore spaces
                        .filter(s -> !Objects.equals(" ", s))
                        .collect(Collectors.joining());
                longs.add(Long.parseLong(longVal));
            }

            if (Objects.equals("+", operations.get(i))) {
                sum += longs.stream().mapToLong(l -> l).sum();
            } else {
                long tmp = longs.getFirst();
                for (int j = 1; j < longs.size(); j++) {
                    tmp = tmp * longs.get(j);
                }
                sum += tmp;
            }
        }
        return sum;
    }
}