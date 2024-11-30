package site.ayrilea.advent.input;

import java.util.List;
import java.util.stream.Stream;

public interface Input {

    List<String> list();

    Stream<String> stream();

    String string();
}
