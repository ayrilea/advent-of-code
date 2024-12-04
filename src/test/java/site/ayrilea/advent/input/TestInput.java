package site.ayrilea.advent.input;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class TestInput implements Input {

    private final List<String> content;

    private TestInput(Builder builder) {
        this.content = List.copyOf(builder.content);
    }

    @Override
    public List<String> list() {
        return content;
    }

    @Override
    public Stream<String> stream() {
        return content.stream();
    }

    @Override
    public String string() {
        return String.join("\\n", content);
    }

    public static TestInput inputOf(String line) {
        TestInput.Builder builder = new Builder();
        Arrays.stream(line.split("\n")).forEach(builder::line);
        return builder.build();
    }

    public static class Builder {

        private final List<String> content = new LinkedList<>();

        public TestInput build() {
            return new TestInput(this);
        }

        public Builder line(int line) {
            content.add(String.valueOf(line));
            return this;
        }

        public Builder line(String line) {
            content.add(line);
            return this;
        }

        public Builder lines(Collection<String> lines) {
            content.addAll(lines);
            return this;
        }

        public Builder lines(String... lines) {
            content.addAll(List.of(lines));
            return this;
        }
    }
}
