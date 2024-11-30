package site.ayrilea.advent.input;

public class FileInputException extends RuntimeException {

    public FileInputException(String message) {
        super(message);
    }

    public FileInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileInputException(Throwable cause) {
        super(cause);
    }
}
