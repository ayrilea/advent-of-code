package site.ayrilea.advent.solution.year2025.day6;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SolutionMetadata(year = 2025, day = 6, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        List<String> lines = input.list();

        List<List<Long>> numbers = new ArrayList<>(lines.size() - 1);
        for (int i = 0; i < lines.size() - 1; i++) {
            List<Long> longs = Arrays.stream(
                    lines.get(i).split("\\s+"))
                    .filter(l -> !l.isBlank())
                    .map(Long::parseLong)
                    .toList();
            numbers.add(longs);
        }
        List<String> operations = Arrays.stream(
                lines.getLast()
                        .split("\\s"))
                .filter(l -> !l.isBlank())
                .toList();

        long sum = 0L;
        for (int i = 0; i < numbers.getFirst().size(); i++) {
            int index = i;
            List<Long> longs = numbers.stream()
                    .map(list -> list.get(index))
                    .toList();
            if (Objects.equals("+", operations.get(index))) {
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