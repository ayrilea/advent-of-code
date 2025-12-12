package site.ayrilea.advent.solution.year2025.day12;

import java.util.*;

record Present(int area) {

    static Present fromInput(List<String> lines) {
        return new Present(lines.stream()
                .map(l -> l.replace(".", ""))
                .map(String::length)
                .mapToInt(i -> i)
                .sum());
    }
}
