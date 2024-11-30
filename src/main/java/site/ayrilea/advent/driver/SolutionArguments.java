package site.ayrilea.advent.driver;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class SolutionArguments {

    private static final String ARGUMENT_NAME_DAY = "day";
    private static final String ARGUMENT_NAME_PART = "part";
    private static final String ARGUMENT_NAME_YEAR = "year";
    private static final Set<String> ARGUMENT_NAMES = Set.of(ARGUMENT_NAME_DAY, ARGUMENT_NAME_PART, ARGUMENT_NAME_YEAR);

    private final int day;
    private final int part;
    private final int year;

    private SolutionArguments(int day, int part, int year) {
        this.day = day;
        this.part = part;
        this.year = year;
    }

    public static SolutionArguments parseArgs(String[] args) {
        if (args == null || args.length != ARGUMENT_NAMES.size()) {
            throw new IllegalArgumentException("Invalid solution arguments: year, day and part are all required");
        }

        Map<String, String> arguments = Arrays.stream(args)
                .map(Argument::fromString)
                .collect(toMap(Argument::name, Argument::value, (a,_) -> a));

        int day = getValue(arguments, ARGUMENT_NAME_DAY);
        int part = getValue(arguments, ARGUMENT_NAME_PART);
        int year = getValue(arguments, ARGUMENT_NAME_YEAR);

        return new SolutionArguments(day, part, year);
    }

    public int getDay() {
        return day;
    }

    public int getPart() {
        return part;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("year=").append(year)
                .append(", day=").append(day)
                .append(", part=").append(part)
                .toString();
    }

    private static int getValue(Map<String, String> arguments, String name) {
        if (arguments.containsKey(name)) {
            return Integer.parseInt(arguments.get(name));
        }
        throw new IllegalArgumentException("Invalid solution arguments: " + name + " is required");
    }

    private record Argument(String name, String value) {

        private static final Pattern ARGUMENT_PATTERN = Pattern.compile("(?<name>[a-z]+)=(?<value>\\d+)");

        private static Argument fromString(String arg) {
            Matcher matcher = ARGUMENT_PATTERN.matcher(arg);
            if (matcher.matches()) {
                String name = matcher.group("name");
                if (ARGUMENT_NAMES.contains(name)) {
                    return new Argument(name, matcher.group("value"));
                }
                throw new IllegalArgumentException(
                        "Invalid solution arguments: " + name + " is not a valid argument name");
            }
            throw new IllegalArgumentException("Invalid solution arguments: " + arg + " is not a valid argument");
        }
    }
}
