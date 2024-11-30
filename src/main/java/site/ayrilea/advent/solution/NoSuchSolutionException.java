package site.ayrilea.advent.solution;

public class NoSuchSolutionException extends RuntimeException {

    public NoSuchSolutionException(String message) {
        super(message);
    }

    public NoSuchSolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchSolutionException(Throwable cause) {
        super(cause);
    }
}
