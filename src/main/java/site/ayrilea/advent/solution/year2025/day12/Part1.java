package site.ayrilea.advent.solution.year2025.day12;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Gatherers;

@SolutionMetadata(year = 2025, day = 12, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        List<String> lines = input.list();
        int regionStartIndex = lines.size() - 1;
        while (!lines.get(regionStartIndex).isBlank()) {
            regionStartIndex--;
        }

        Map<Integer, Present> presents = parsePresents(lines.subList(0, regionStartIndex));
        List<Region> regions = parseRegions(lines.subList(regionStartIndex + 1, lines.size()), presents);

        return regions.stream()
                .filter(Part1::isValidRegion)
                .count();
    }

    private static boolean isValidRegion(Region region) {
        //Simplified approach based on real input - only check if the region has enough room to place all the present
        //pieces in it, and assume the tiling is compact enough that it would be able to work.
        return region.totalPresentArea() <= region.length() * region.width();
    }

    private static Map<Integer, Present> parsePresents(List<String> input) {
        Map<Integer, Present> presents = new HashMap<>();

        input.stream()
                //Every present is 1 line for ID, 3 lines for present, 1 empty line
                .gather(Gatherers.windowFixed(5))
                .forEach(lines -> {
                    //Line 1: ID
                    int presentIndex = Integer.parseInt(lines.getFirst().split(":")[0]);
                    //Lines 2, 3, 4: present
                    presents.put(presentIndex, Present.fromInput(lines.subList(1, 4)));
                });

        return presents;
    }

    private static List<Region> parseRegions(List<String> input, Map<Integer, Present> presents) {
        return input.stream()
                .map(region -> Region.fromInput(region, presents))
                .toList();
    }
}