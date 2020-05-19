package data.Exception;

/**
 * {@code SessionNotFoundException} defines an Exception that
 * is used when a Session cannot be found in the {@code SessionWishlist}
 * @see NotFoundException
 * @see data.Database.SessionWishlist
 * @author Lucas Demchik
 * @version 0.1
 */
public class SessionNotFoundException extends NotFoundException {
    public SessionNotFoundException(String s) { super(s); }
}
