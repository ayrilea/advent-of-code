package site.ayrilea.advent.generator;

public class GeneratorDriver {

    static void main(String[] args) {
        int year = parseArgs(args);

        new Generator(year).generate();
        System.out.println("Advent of Code " + year + " skeleton generated successfully!");
    }

    private static int parseArgs(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid generator arguments. " +
                    "Single argument representing year is required.");
        }

        try {
            return Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year must be a valid number.");
        }
    }
}
