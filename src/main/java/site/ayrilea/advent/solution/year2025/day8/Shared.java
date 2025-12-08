package site.ayrilea.advent.solution.year2025.day8;

import site.ayrilea.advent.input.Input;

import java.util.*;
import java.util.stream.Collectors;

class Shared {

    static void connectPair(List<Set<Junction>> circuits, Pair pair) {
        Set<Junction> first = findCircuitForJunction(circuits, pair.first());
        Set<Junction> second = findCircuitForJunction(circuits, pair.second());

        //If the two junctions are in different circuits then merge the circuits
        if (first != second) {
            circuits.remove(second);
            first.addAll(second);
        }
    }

    static List<Pair> getClosestPairs(ParsedInput parsedInput, boolean useLimit) {
        List<Junction> junctions = parsedInput.junctions();
        Map<Double, Pair> pairByDistance = new HashMap<>();
        for (int i = 0; i < junctions.size(); i++) {
            Junction first = junctions.get(i);
            for (int j = i + 1; j < junctions.size(); j++) {
                Junction second = junctions.get(j);
                pairByDistance.put(straightLineDistance(first, second), new Pair(first, second));
            }
        }

        return pairByDistance.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .limit(useLimit ? parsedInput.limit() : pairByDistance.size())
                .collect(Collectors.toList());
    }

    static List<Set<Junction>> getStartingCircuits(ParsedInput parsedInput) {
        return parsedInput.junctions()
                .stream()
                .map(junction -> {
                    Set<Junction> circuit = new HashSet<>();
                    circuit.add(junction);
                    return circuit;
                })
                .collect(Collectors.toList());
    }

    static ParsedInput parseInput(Input input) {
        List<String> lines = input.list();
        int limit = Integer.parseInt(lines.getFirst());

        List<Junction> junctions = lines.subList(1, lines.size()).stream()
                .map(Junction::fromInput)
                .toList();

        return new ParsedInput(junctions, limit);
    }

    private static Set<Junction> findCircuitForJunction(List<Set<Junction>> circuits, Junction junction) {
        return circuits.stream()
                .filter(c -> c.stream().anyMatch(j -> Objects.equals(j, junction)))
                .findFirst()
                .orElseThrow();
    }

    private static double straightLineDistance(Junction first, Junction second) {
        return Math.sqrt(Math.pow(first.x() - second.x(), 2) +
                Math.pow(first.y() - second.y(), 2) +
                Math.pow(first.z() - second.z(), 2)
        );
    }
}
