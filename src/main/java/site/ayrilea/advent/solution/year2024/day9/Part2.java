package site.ayrilea.advent.solution.year2024.day9;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.List;

@SolutionMetadata(year = 2024, day = 9, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part2::defragment);
    }

    private static List<Long> defragment(List<Long> blocks) {
        for (long locationId = blocks.getLast(); locationId >= 0; locationId--) {
            int startOfLocationId = 0;
            while (blocks.get(startOfLocationId) != locationId) {
                startOfLocationId++;
            }
            int fileSize = 0;
            int endOfLocationId = startOfLocationId;
            while (endOfLocationId < blocks.size() && blocks.get(endOfLocationId) == locationId) {
                fileSize++;
                endOfLocationId++;
            }

            int freeSpace = 0;
            int startOfFreeSpace = 0;
            int currentIndex = 0;
            while (startOfFreeSpace < startOfLocationId && freeSpace < fileSize) {
                while (blocks.get(currentIndex) == -1) {
                    freeSpace++;
                    currentIndex++;
                }
                if (freeSpace >= fileSize) {
                    break;
                }
                currentIndex++;
                freeSpace = 0;
                startOfFreeSpace = currentIndex;
            }

            if (startOfFreeSpace < startOfLocationId) {
                for (int i = startOfFreeSpace; i < startOfFreeSpace + fileSize; i++) {
                    blocks.set(i, locationId);
                }
                for (int i = startOfLocationId; i < endOfLocationId; i++) {
                    blocks.set(i, -1L);
                }
            }
        }

        return blocks;
    }
}
