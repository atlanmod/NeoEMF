package fr.inria.atlanmod.neoemf.data.store;

/**
 * Exception thrown when trying to call a closed {@link Store}.
 */
public class ClosedStoreException extends IllegalStateException {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -7365877931773274125L;

    /**
     * Constructs an {@code ClosedStoreException} with {@code null} as its error detail message.
     */
    public ClosedStoreException() {
    }

    /**
     * Constructs an {@code ClosedStoreException} with the specified detail {@code message}.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public ClosedStoreException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ClosedStoreException} with the specified {@code cause} and its detail message.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *              value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ClosedStoreException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an {@code ClosedStoreException} with the specified detail {@code message} and {@code cause}.
     * <p>
     * The detail message associated with cause is not automatically incorporated into this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *                value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ClosedStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
