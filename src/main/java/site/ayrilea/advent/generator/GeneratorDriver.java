package site.ayrilea.advent.generator;

public class GeneratorDriver {

    static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Generator <year>");
            System.err.println("Example: java Generator 2025");
            System.exit(1);
        }

        int year;
        try {
            year = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Year must be a valid number");
            System.exit(1);
            return;
        }

        new Generator(year).generate();
        System.out.println("Advent of Code " + year + " skeleton generated successfully!");
    }
}
