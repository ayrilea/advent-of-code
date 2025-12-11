package site.ayrilea.advent.solution.year2025.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

record Device(String label, List<Device> outputs) {

    Device(String label) {
        this(label, new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(label, device.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
