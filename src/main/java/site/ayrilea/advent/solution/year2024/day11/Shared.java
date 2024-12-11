package site.ayrilea.advent.solution.year2024.day11;

import site.ayrilea.advent.input.Input;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.*;

class Shared {

    static long solveFor(Input input, int blinks) {
        return Shared.getStoneCount(Arrays.stream(input.string()
                        .split(" "))
                .map(Long::parseLong)
                .toList(), blinks);
    }

    private static long getStoneCount(List<Long> stones, int blinks) {
        List<Stone> temp = stones.stream()
                .map(stone -> new Stone(stone, 1))
                .toList();
        for (int i = 0; i < blinks; i++) {
            temp = temp.stream()
                    .map(Shared::blink)
                    .flatMap(Collection::stream)
                    .collect(groupingBy(Stone::value, summingLong(Stone::count)))
                    .entrySet()
                    .stream()
                    .map(e -> new Stone(e.getKey(), e.getValue()))
                    .toList();
        }
        return temp.stream().map(Stone::count).mapToLong(l -> l).sum();
    }

    private static List<Stone> blink(Stone stone) {
        if (stone.value == 0) {
            return List.of(new Stone(1L, stone.count));
        }
        String digits = String.valueOf(stone.value);
        if (digits.length() % 2 == 0) {
            long first = Long.parseLong(digits.substring(0, digits.length() / 2));
            long second = Long.parseLong(digits.substring(digits.length() / 2));
            return List.of(new Stone(first, stone.count), new Stone(second, stone.count));
        }
        return List.of(new Stone(2024L * stone.value, stone.count));
    }

    private record Stone(long value, long count) {
    }
}
