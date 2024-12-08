package site.ayrilea.advent.solution.year2024.day8;

import site.ayrilea.advent.input.Input;

import java.util.*;

class Shared {

    static boolean isInMap(int row, int column, int mapRowMax, int mapColumnMax) {
        return row >= 0 && column >= 0 && row <= mapRowMax && column <= mapColumnMax;
    }

    static long solve(Input input, PairToAntinodes pairToAntinodes) {
        int mapMaxRow = input.list().size() - 1;
        int mapMaxColumn = input.list().getFirst().length() - 1;

        return parseInput(input).values()
                .stream()
                .map(Shared::positionsToPairs)
                .flatMap(List::stream)
                .map(pair -> pairToAntinodes.apply(pair, mapMaxRow, mapMaxColumn))
                .flatMap(Set::stream)
                .distinct()
                .count();
    }

    private static Map<Character, List<Position>> parseInput(Input input) {
        Map<Character, List<Position>> positionsByLabel = new HashMap<>();

        List<String> lines = input.list();
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.getFirst().length(); column++) {
                char current = lines.get(row).charAt(column);
                if (!('.' == current)) {
                    Position position = new Position(row, column);
                    positionsByLabel.computeIfAbsent(current, _ -> new LinkedList<>()).add(position);
                }
            }
        }

        return positionsByLabel;
    }

    private static List<Pair> positionsToPairs(List<Position> positions) {
        List<Pair> pairs = new LinkedList<>();
        for (int i = 0; i < positions.size(); i++) {
            for (int j = i + 1; j < positions.size(); j++) {
                pairs.add(new Pair(positions.get(i), positions.get(j)));
            }
        }
        return pairs;
    }
}
