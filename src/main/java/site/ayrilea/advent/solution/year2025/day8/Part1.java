package site.ayrilea.advent.solution.year2025.day8;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.*;

@SolutionMetadata(year = 2025, day = 8, part = 1)
public class Part1 implements Solution<Integer> {

    @Override
    public Integer solve(Input input) {
        List<String> lines = input.list();
        int limit = Integer.parseInt(lines.getFirst());

        List<Junction> junctions = lines.subList(1, lines.size()).stream()
                .map(Junction::fromInput)
                .toList();

        Map<Double, Pair> pairByDistance = new HashMap<>();
        for (int i = 0; i < junctions.size(); i++) {
            Junction first = junctions.get(i);
            for (int j = i + 1; j < junctions.size(); j++) {
                Junction second = junctions.get(j);
                pairByDistance.put(straightLineDistance(first, second), new Pair(first, second));
            }
        }

        List<Pair> closestPairs = pairByDistance.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .limit(limit)
                .toList();

        List<Set<Junction>> circuits = new ArrayList<>();
        for (Pair pair : closestPairs) {
            List<Set<Junction>> matching = circuits.stream()
                    .filter(c -> c.stream().anyMatch(pair::contains))
                    .toList();
            if (matching.isEmpty()) {
                Set<Junction> newCircuit = new HashSet<>();
                newCircuit.add(pair.first());
                newCircuit.add(pair.second());
                circuits.add(newCircuit);
            } else if (matching.size() == 1) {
                Set<Junction> existingCircuit = matching.getFirst();
                existingCircuit.add(pair.first());
                existingCircuit.add(pair.second());
            } else {
                Set<Junction> first = matching.getFirst();
                Set<Junction> last = matching.getLast();
                circuits.remove(last);
                first.addAll(last);
            }
        }

        return circuits.stream()
                .map(Set::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

    private static double straightLineDistance(Junction first, Junction second) {
        return Math.sqrt(Math.pow(first.x() - second.x(), 2) +
                Math.pow(first.y() - second.y(), 2) +
                Math.pow(first.z() - second.z(), 2)
        );
    }

}