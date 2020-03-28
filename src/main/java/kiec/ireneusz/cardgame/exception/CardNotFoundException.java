package kiec.ireneusz.cardgame.exception;

public class CardNotFoundException extends AppException {
    private static String NOT_FOUND = "Card not foundr";
    private static String NOT_FOUND_ID = "Card id not found: ";

    public CardNotFoundException() {
        super(NOT_FOUND);
    }
    public CardNotFoundException(Long gameId) {
        super(NOT_FOUND_ID + gameId);
    }

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException(Class clazz) {
        super(NOT_FOUND, clazz.getSimpleName().toUpperCase());
    }
}
