package lt.codeacademy.games.exception;

public class DuplicateUserGameException extends RuntimeException {
    public DuplicateUserGameException(String message) {
        super(message);
    }
}
