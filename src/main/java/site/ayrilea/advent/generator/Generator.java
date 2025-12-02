package site.ayrilea.advent.generator;

import site.ayrilea.advent.input.FileInputException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Generator {

    private static final String RUN_CONFIGURATION_NAME_FORMAT = "Solution___%d_Day_%d_Part_%d.xml";
    private static final String SOLUTION_PACKAGE_FORMAT = "site.ayrilea.advent.solution.year%d.day%d";

    private final Path projectRoot;
    private final String templateRunConfiguration;
    private final String templateSolution;
    private final String templateTest;
    private final int year;

    public Generator(int year) {
        this.year = year;

        projectRoot = findProjectRoot();
        templateRunConfiguration = readTemplate("SolutionRunConfiguration.xml.template");
        templateSolution = readTemplate("Solution.java.template");
        templateTest = readTemplate("SolutionTest.java.template");
    }

    public void generate() {
        try {
            generateClassFiles();
            generateInputFiles();
            generateRunConfigurations();
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

    private void generateClassFiles() throws IOException {
        Path mainSrc = projectRoot.resolve(Paths.get("src", "main", "java", "site", "ayrilea", "advent",
                "solution", "year" + year));
        Path testSrc = projectRoot.resolve(Paths.get("src", "test", "java", "site", "ayrilea", "advent",
                "solution", "year" + year));

        Files.createDirectories(mainSrc);
        Files.createDirectories(testSrc);

        for (int day = 1; day <= 12; day++) {
            Path daySolution = mainSrc.resolve("day" + day);
            Path dayTest = testSrc.resolve("day" + day);

            Files.createDirectories(daySolution);
            Files.createDirectories(dayTest);

            String packageName = SOLUTION_PACKAGE_FORMAT.formatted(year, day);
            for (int part = 1; part <= 2; part++) {
                String solutionClassName = "Part" + part;
                String testClassName = solutionClassName + "Test";

                String solutionClass = templateSolution.formatted(packageName, year, day, part, solutionClassName);
                String testClass = templateTest.formatted(packageName, year, day, part, testClassName, solutionClassName);

                writeClassFile(daySolution, solutionClassName, solutionClass);
                writeClassFile(dayTest, testClassName, testClass);
            }
        }
    }

    private void generateInputFiles() throws IOException {
        Path mainResources = projectRoot.resolve(Paths.get("src", "main", "resources", "site", "ayrilea",
                "advent", "input", "year" + year));
        Path testResources = projectRoot.resolve(Paths.get("src", "test", "resources", "site", "ayrilea",
                "advent", "input", "year" + year));

        Files.createDirectories(mainResources);
        Files.createDirectories(testResources);

        for (int day = 1; day <= 12; day++) {
            writeInputFile(mainResources, day);
            writeInputFile(testResources, day);
        }
    }

    private void generateRunConfigurations() throws IOException {
        Path runConfigurations = projectRoot.resolve(Paths.get(".idea", "runConfigurations"));

        for (int day = 1; day <= 12; day++) {
            for (int part = 1; part <= 2; part++) {
                String runConfigurationName = RUN_CONFIGURATION_NAME_FORMAT.formatted(year, day, part);
                String runConfiguration = templateRunConfiguration.formatted(year, day, part, year, day, part);

                writeRunConfigurationFile(runConfigurations, runConfigurationName, runConfiguration);
            }

        }
    }

    private String readTemplate(String filename) {
        URL resource = Generator.class.getResource(filename);
        if (resource == null) {
            throw new FileInputException(filename + " does not exist");
        }
        try {
            Path path = Paths.get(resource.toURI());
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Failed to retrieve path for " + resource, e);
        }
    }

    private void writeClassFile(Path basePath, String className, String content) throws IOException {
        Path file = basePath.resolve(className + ".java");
        if (!Files.exists(file)) {
            Files.writeString(file, content.stripTrailing());
            System.out.println("Created class file: " + file);
        } else {
            System.out.println("Skipped class file (already exists): " + file);
        }
    }

    private void writeInputFile(Path basePath, int day) throws IOException {
        Path file = basePath.resolve("day" + day + ".txt");
        if (!Files.exists(file)) {
            Files.createFile(file);
            System.out.println("Created input file: " + file);
        } else {
            System.out.println("Skipped input file (already exists): " + file);
        }
    }

    private void writeRunConfigurationFile(Path basePath, String fileName, String content) throws IOException {
        Path file = basePath.resolve(fileName);
        if (!Files.exists(file)) {
            Files.writeString(file, content.stripTrailing());
            System.out.println("Created run configuration file: " + file);
        } else {
            System.out.println("Skipped run configuration file (already exists): " + file);
        }
    }
}