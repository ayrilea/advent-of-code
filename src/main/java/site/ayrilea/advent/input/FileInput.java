package site.ayrilea.advent.input;

import site.ayrilea.advent.driver.SolutionArguments;
import site.ayrilea.advent.solution.SolutionMetadata;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileInput implements Input {

    private static final String FILE_NAME_FORMAT = "year%d/day%d.txt";

    private final Path path;

    private FileInput(Path path) {
        this.path = path;
    }

    @Override
    public List<String> list() {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + path, e);
        }
    }

    @Override
    public Stream<String> stream() {
        //Files.stream(..) could be used instead to lazily stream the lines of the file, avoiding needing to read the
        //entire file into memory.
        //
        //However, this would mean needing to handle closing the returned Stream (to ensure the open file is promptly
        //closed). This would mean the consumer would either need to do the try-with-resources or similar, or this API
        //would need to take a function to execute on the lines. To keep this API consistent with other Input APIs, this
        //instead is just a helper for streaming the returned List from Files.readAllLines(..).
        return list().stream();
    }

    @Override
    public String string() {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileInputException("Failed to read " + path, e);
        }
    }

    public static FileInput inputFor(SolutionArguments arguments) {
        return inputFor(arguments.getYear(), arguments.getDay());
    }

    public static FileInput inputFor(SolutionMetadata metadata) {
        return inputFor(metadata.year(), metadata.day());
    }

    private static FileInput inputFor(int year, int day) {
        URL resource = FileInput.class.getResource(String.format(FILE_NAME_FORMAT, year, day));
        if (resource == null) {
            throw new FileInputException("Input for [year=" + year + ", day=" + day + "] does not exist");
        }
        try {
            return new FileInput(Paths.get(resource.toURI()));
        } catch (URISyntaxException e) {
            throw new FileInputException("Failed to retrieve path for " + resource, e);
        }
    }
}
