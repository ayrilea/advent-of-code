package site.ayrilea.advent.solution.year2025.day11;

import java.util.ArrayList;
import java.util.List;

record Device(String label, List<Device> outputs) {

    Device(String label) {
        this(label, new ArrayList<>());
    }
}
