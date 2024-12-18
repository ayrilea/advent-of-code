package site.ayrilea.advent.solution.year2024.day17;

import site.ayrilea.advent.input.Input;
import site.ayrilea.advent.solution.Solution;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.util.Comparator;
import java.util.List;

import static site.ayrilea.advent.solution.year2024.day17.Shared.*;

@SolutionMetadata(year = 2024, day = 17, part = 2)
public class Part2 implements Solution<Long> {

    @Override
    public Long solve(Input input) {
//        mapToInstructions(
//                extractProgram(input.list().getLast()))
//                .forEach(System.out::println);
//
//        for (int i = 0; i < 8; i++) {
//            System.out.println(i + ": " + (i ^ 5));
//        }

        //Real input includes loop that divides A / 8 until 0, and has 16 outputs
        //So A must be between 8^15 and 8^16 - 1
        //
        //Real input must end in "5,0,3,5,5,3,0", which only comes from the final seven loops starting with 1225925 or
        //1225963. Select lower value for problem (asks for lowest value of A that prints program again).
        //
        //minA = 2^9 * 1225925
        //maxA = 2^9 * 1225926
        //9 comes from output size (16) minus precalculated positions (7)
        long minA = 164540868198400L;
        long maxA = 164541002416128L;

        List<String> lines = input.list();
        List<Instruction> instructions = mapToInstructions(extractProgram(lines.getLast())).toList();
        String program = extractProgram(lines.getLast());

        for (long a = minA; a < maxA; a++) {
            Computer computer = Computer.forInstructions(instructions);
            computer.setRegisterA(a);
            computer.run();
            if (program.equals(computer.getOutput())) {
                return a;
            }
        }


//        for (int i = 0; i < 2097152; i++) {
//            Computer computer = parseInput(input.list());
//            computer.setRegisterA(i);
//            computer.run();
//            if (computer.getOutput().endsWith("5,0,3,5,5,3,0")) {
//                System.out.println(i + ": " + computer.getOutput());
//            }
//        }

        return 0L;
    }
}