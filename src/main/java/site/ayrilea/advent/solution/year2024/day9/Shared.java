package site.ayrilea.advent.solution.year2024.day9;

import site.ayrilea.advent.input.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class Shared {

    static long solve(Input input, Function<List<Long>, List<Long>> defragment) {
        return calculateChecksum(defragment.apply(parseInput(input)));
    }

    private static long calculateChecksum(List<Long> blocks) {
        long checksum = 0;
        for (int i = 0; i < blocks.size(); i++) {
            long locationId = blocks.get(i);
            if (locationId != -1L) {
                checksum += i * blocks.get(i);
            }
        }
        return checksum;
    }

    private static List<Long> parseInput(Input input) {
        String[] parts = input.string().split("");
        List<Long> blocks = new ArrayList<>();
        boolean isFile = true;
        int locationId = 0;
        for (int i = 0; i < parts.length; i++) {
            long current = Long.parseLong(parts[i]);
            for (int j = 0; j < current; j++) {
                blocks.add(isFile ? locationId : -1L);
            }
            if (isFile) {
                locationId++;
            }
            isFile = !isFile;
        }
        return blocks;
    }
}
