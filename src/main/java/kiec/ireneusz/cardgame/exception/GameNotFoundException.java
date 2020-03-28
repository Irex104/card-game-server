package kiec.ireneusz.cardgame.exception;

public class GameNotFoundException extends AppException {
    private static String NOT_FOUND = "Game not foundr";
    private static String NOT_FOUND_ID = "Game id not found: ";

    public GameNotFoundException() {
        super(NOT_FOUND);
    }
    public GameNotFoundException(Long gameId) {
        super(NOT_FOUND_ID + gameId);
    }

    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(Class clazz) {
        super(NOT_FOUND, clazz.getSimpleName().toUpperCase());
    }
}
