package site.ayrilea.advent.solution.year2024.day11;

import java.util.*;

class StoneTransformer {

    private final int maxBlinks;
    private final Map<Long, Map<Integer, Long>> countsByStone;

    private StoneTransformer(int maxBlinks) {
        this.maxBlinks = maxBlinks;

        countsByStone = new HashMap<>();
    }

    static StoneTransformer forBlinks(int blinks) {
        return new StoneTransformer(blinks);
    }

    long getNumberOfStones(long stone) {
        if (countsByStone.computeIfAbsent(stone, _ -> new HashMap<>()).containsKey(maxBlinks)) {
            return countsByStone.get(stone).get(maxBlinks);
        }
        countsByStone.get(stone).put(0, 1L);

        return getNumberOfStones(stone, maxBlinks);
    }

    long getNumberOfStones(long stone, int maxBlinks) {
        var stoneCounts = countsByStone.computeIfAbsent(stone, _ -> new HashMap<>());
        stoneCounts.putIfAbsent(0, 1L);

        long current = stone;
        for (int blink = 1; blink <= maxBlinks; blink++) {
            var counts = countsByStone.computeIfAbsent(current, _ -> new HashMap<>());
            if (counts.containsKey(maxBlinks - blink + 1)) {
                return counts.get(maxBlinks - blink + 1);
            }

            if (current == 0L) {
                current = 1L;
                long previousCount = stoneCounts.get(blink - 1);
                stoneCounts.put(blink, previousCount);
            } else {
                String digits = String.valueOf(current);
                if (digits.length() % 2 == 0) {
                    long first = Long.parseLong(digits.substring(0, digits.length() / 2));
                    long second = Long.parseLong(digits.substring(digits.length() / 2));
                    long total = getNumberOfStones(first, maxBlinks - blink) +
                            getNumberOfStones(second, maxBlinks - blink);
                    stoneCounts.put(maxBlinks, total);
                    break;
                } else {
                    current *= 2024L;
                    long previousCount = stoneCounts.get(blink - 1);
                    stoneCounts.put(blink, previousCount);
                }
            }
        }
        return countsByStone.get(stone).get(maxBlinks);
    }
}
