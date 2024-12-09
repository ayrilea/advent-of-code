package site.ayrilea.advent.solution.year2024.day9;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.LinkedList;
import java.util.List;

@SolutionMetadata(year = 2024, day = 9, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part1::defragment);
    }

    private static List<Long> defragment(List<Long> blocks) {
        List<Long> defragmented = new LinkedList<>();
        int lastFileBlock = blocks.size() - 1;
        for (int i = 0; i <= lastFileBlock; i++) {
            long current = blocks.get(i);
            if (current == -1) {
                defragmented.add(i, blocks.get(lastFileBlock));
                lastFileBlock--;
                while (blocks.get(lastFileBlock) == -1) {
                    lastFileBlock--;
                }
            } else {
                defragmented.add(i, current);
            }
        }
        return defragmented;
    }
}
