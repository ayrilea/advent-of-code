package site.ayrilea.advent.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generator {

    private static final String SOLUTION_PACKAGE = "site.ayrilea.advent.solution";

    private final Path projectRoot;
    private final int year;

    public Generator(int year) {
        this.projectRoot = findProjectRoot();
        this.year = year;
    }

    public void generate() {
        try {
            generateSolutionClasses();
            generateTestClasses();
            generateMainInputFiles();
            generateTestInputFiles();
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate skeleton for year " + year, e);
        }
    }

    private Path findProjectRoot() {
        Path current = Paths.get("").toAbsolutePath();
        while (current != null) {
            if (Files.exists(current.resolve("build.gradle")) ||
                    Files.exists(current.resolve("pom.xml")) ||
                    Files.exists(current.resolve("gradlew"))) {
                return current;
            }
            current = current.getParent();
        }
        throw new IllegalStateException("Could not find project root (no build.gradle, pom.xml or gradlew found)");
    }

    private void generateSolutionClasses() throws IOException {
        Path basePath = projectRoot.resolve(Paths.get("src", "main", "java", "site", "ayrilea", "advent",
                "solution", "year" + year));

        for (int day = 1; day <= 25; day++) {
            Path dayPackage = basePath.resolve("day" + day);
            Files.createDirectories(dayPackage);

            for (int part = 1; part <= 2; part++) {
                String className = "Part" + part;
                String packageName = SOLUTION_PACKAGE + ".year" + year + ".day" + day;

                String content = """
                    package %s;

                    import site.ayrilea.advent.input.Input;
                    import site.ayrilea.advent.solution.Solution;
                    import site.ayrilea.advent.solution.SolutionMetadata;

                    @SolutionMetadata(year = %d, day = %d, part = %d)
                    public class %s implements Solution<Integer> {

                        @Override
                        public Integer solve(Input input) {
                            return 0;
                        }
                    }
                    """.formatted(packageName, year, day, part, className);

                Path file = dayPackage.resolve(className + ".java");
                Files.writeString(file, content.stripTrailing());
                System.out.println("Created solution class file: " + file);
            }
        }
    }

    private void generateTestClasses() throws IOException {
        Path basePath = projectRoot.resolve(Paths.get("src", "test", "java", "site", "ayrilea",
                "advent", "solution", "year" + year));

        for (int day = 1; day <= 25; day++) {
            Path dayPackage = basePath.resolve("day" + day);
            Files.createDirectories(dayPackage);

            for (int part = 1; part <= 2; part++) {
                String partClass = "Part" + part;
                String testClassName = partClass + "Test";
                String packageName = SOLUTION_PACKAGE + ".year" + year + ".day" + day;

                String content = """
                    package %s;

                    import org.junit.jupiter.api.DisplayName;
                    import site.ayrilea.advent.solution.AbstractSolutionTest;
                    import site.ayrilea.advent.solution.Solution;

                    @DisplayName("Year %d, Day %d, Part %d")
                    public class %s extends AbstractSolutionTest<Integer> {

                        @Override
                        protected Solution<Integer> createSolution() {
                            return new %s();
                        }

                        @Override
                        protected Integer exampleExpectedValue() {
                            return 0;
                        }
                    }
                    """.formatted(packageName, year, day, part, testClassName, partClass);

                Path file = dayPackage.resolve(testClassName + ".java");
                Files.writeString(file, content.stripTrailing());
                System.out.println("Created solution test class file: " + file);
            }
        }
    }

    private void generateMainInputFiles() throws IOException {
        Path basePath = projectRoot.resolve(Paths.get("src", "main", "resources", "site", "ayrilea",
                "advent", "input", "year" + year));
        Files.createDirectories(basePath);

        for (int day = 1; day <= 25; day++) {
            Path file = basePath.resolve("day" + day + ".txt");
            if (!Files.exists(file)) {
                Files.createFile(file);
                System.out.println("Created problem input file: " + file);
            } else {
                System.out.println("Skipped problem input file (already exists): " + file);
            }
        }
    }

    private void generateTestInputFiles() throws IOException {
        Path basePath = projectRoot.resolve(Paths.get("src", "test", "resources", "site", "ayrilea",
                "advent", "input", "year" + year));
        Files.createDirectories(basePath);

        for (int day = 1; day <= 25; day++) {
            Path file = basePath.resolve("day" + day + ".txt");
            if (!Files.exists(file)) {
                Files.createFile(file);
                System.out.println("Created test input file: " + file);
            } else {
                System.out.println("Skipped test input file (already exists): " + file);
            }
        }
    }
}