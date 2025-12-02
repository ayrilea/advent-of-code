package site.ayrilea.advent.solution.year2025.day2;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SolutionMetadata(year = 2025, day = 2, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
        return Shared.solve(input, Part2::isInvalid);
    }

    private static Stream<String> fixedChunks(String string, int size) {
        return IntStream.range(0, string.length() / size)
                .mapToObj(i -> string.substring(i * size, (i + 1) * size));
    }

    private static boolean isInvalid(long id) {
        String label = Long.toString(id);

        //The minimum sequence length is one digit (repeated for the entire ID), and the maximum sequence length is half
        //the size of the ID (the same sequence repeated twice for an even length ID).
        for (int sequenceLength = 1; sequenceLength <= label.length() / 2; sequenceLength++) {
            //The entire ID can only be made up of the repeated sequence of the sequence length divides evenly into the
            //length of the ID
            if (label.length() % sequenceLength != 0) {
                continue;
            }

            //Only the prefix of the sequence length needs to be checked, since if the whole label is a repeated
            //sequence, then it must start with that sequence
            String possibleSequence = label.substring(0, sequenceLength);

            //Split the label into chunks sized to this sequence, then check if _every_ chunk matches the possible
            //sequence (possible optimisation: cache chunks and don't recalculate every possible sequence of same size)
            if (fixedChunks(label, sequenceLength)
                    .allMatch(c -> Objects.equals(possibleSequence, c))) {
                return true;
            }
        }

        return false;
    }
}