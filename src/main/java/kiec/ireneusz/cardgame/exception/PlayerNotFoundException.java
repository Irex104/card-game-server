package kiec.ireneusz.cardgame.exception;

import javassist.NotFoundException;

public class PlayerNotFoundException extends AppException {

    private static String NOT_FOUND = "Player not foundr";
    private static String NOT_FOUND_ID = "Player id not found: ";

    public PlayerNotFoundException() {
        super(NOT_FOUND);
    }
    public PlayerNotFoundException(Long playerId) {
        super(NOT_FOUND_ID + playerId);
    }

    public PlayerNotFoundException(String message) {
        super(message);
    }

    public PlayerNotFoundException(Class clazz) {
        super(NOT_FOUND, clazz.getSimpleName().toUpperCase());
    }

}
