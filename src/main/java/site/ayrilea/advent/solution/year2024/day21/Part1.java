package site.ayrilea.advent.solution.year2024.day21;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.LinkedList;
import java.util.List;

@SolutionMetadata(year = 2024, day = 21, part = 1)
public class Part1 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        List<String> codes = input.list();
        List<Keypad> keypads = getKeypads();

        long complexitySum = 0L;
        for (String code : codes) {
            complexitySum += calculateComplexity(keypads, code);
        }
        return complexitySum;
    }

    private static long calculateComplexity(List<Keypad> keypads, String line) {
        //List<Keypad> keypads = getKeypads();
        String current = line;
        for (Keypad keypad : keypads) {
            current = keypad.inputSequenceFor(current);
            System.out.println(current);
        }
        System.out.println(line + ": " +  current.length() + "\n");
        return (long) current.length() * Integer.parseInt(line.substring(0, line.length() - 1));

        // <v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A
        // v<<A>>^AvA^Av<<A>>^AAv<A<A>>^AAvAA^<A>Av<A>^AA<A>Av<A<A>>^AAAvA^<A>A
    }

    private static List<Keypad> getKeypads() {
        List<Keypad> keypads = new LinkedList<>();
        keypads.add(new NumericKeypad());
        keypads.add(new DirectionalKeypad());
        keypads.add(new DirectionalKeypad());
        return keypads;
    }
}
