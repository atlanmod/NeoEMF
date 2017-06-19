package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.data.store.Store;

/**
 * Exception thrown when an error occurred when creating {@link Store}.
 */
public class InvalidStoreException extends RuntimeException {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -7365877931773274125L;

    /**
     * Constructs an {@code InvalidBackendException} with {@code null} as its error detail message.
     */
    public InvalidStoreException() {
    }

    /**
     * Constructs an {@code InvalidBackendException} with the specified detail {@code message}.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public InvalidStoreException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code InvalidBackendException} with the specified {@code cause} and its detail message.
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *              value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidStoreException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an {@code InvalidBackendException} with the specified detail {@code message} and {@code cause}.
     * <p>
     * The detail message associated with cause is not automatically incorporated into this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method). (A {@code null}
     *                value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
