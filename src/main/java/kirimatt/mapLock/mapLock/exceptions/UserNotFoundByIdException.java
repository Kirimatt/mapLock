package kirimatt.mapLock.mapLock.exceptions;

/**
 * @author kirimatt
 */
public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
