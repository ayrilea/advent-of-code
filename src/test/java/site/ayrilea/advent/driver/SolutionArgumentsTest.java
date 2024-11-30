package site.ayrilea.advent.driver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionArgumentsTest {

    @Test
    public void parseArgs() {
        SolutionArguments args = SolutionArguments.parseArgs(new String[]{"day=1", "part=2", "year=2015"});

        assertEquals(1, args.getDay());
        assertEquals(2, args.getPart());
        assertEquals(2015, args.getYear());
    }

    @Test
    public void parseArgsInvalidArgumentFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"day=1", "part=2", "--year_2015"}));
    }

    @Test
    public void parseArgsInvalidArgumentName() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"day=1", "part=2", "invalid=2015"}));
    }

    @Test
    public void parseArgsInvalidArgumentValue() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"day=1", "part=two", "year=2015"}));
    }

    @Test
    public void parseArgsMissingArgumentDay() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"part=1", "part=2", "year=2015"}));
    }

    @Test
    public void parseArgsMissingArgumentPart() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"day=1", "day=2", "year=2015"}));
    }

    @Test
    public void parseArgsMissingArgumentYear() {
        assertThrows(IllegalArgumentException.class,
                () -> SolutionArguments.parseArgs(new String[]{"day=1", "part=1", "part=2"}));
    }

    @Test
    public void parseArgsNull() {
        assertThrows(IllegalArgumentException.class, () -> SolutionArguments.parseArgs(null));
    }

    @Test
    public void parseArgsNumberOfArgumentsNone() {
        assertThrows(IllegalArgumentException.class, () -> SolutionArguments.parseArgs(new String[0]));
    }

    @Test
    public void parseArgsNumberOfArgumentsTooFew() {
        assertThrows(IllegalArgumentException.class, () -> SolutionArguments.parseArgs(new String[2]));
    }

    @Test
    public void parseArgsNumberOfArgumentsTooMany() {
        assertThrows(IllegalArgumentException.class, () -> SolutionArguments.parseArgs(new String[4]));
    }
}
