package fr.inria.atlanmod.neoemf.logging;

public interface Logger {

    String ROOT_LOGGER_NAME = "";

    /**
     * Logs a message at the {@link Level#DEBUG DEBUG} level.
     */
    default void debug(CharSequence msg) {
        logMessage(Level.DEBUG, null, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#DEBUG DEBUG} level.
     */
    default void debug(CharSequence pattern, Object... args) {
        logMessage(Level.DEBUG, null, pattern, args);
    }

    /**
     * Logs a message at the {@link Level#INFO INFO} level.
     */
    default void info(CharSequence msg) {
        logMessage(Level.INFO, null, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#INFO INFO} level.
     */
    default void info(CharSequence pattern, Object... args) {
        logMessage(Level.INFO, null, pattern, args);
    }

    /**
     * Logs a message at the {@link Level#WARN WARN} level.
     */
    default void warn(CharSequence msg) {
        logMessage(Level.WARN, null, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#WARN WARN} level.
     */
    default void warn(CharSequence pattern, Object... args) {
        logMessage(Level.WARN, null, pattern, args);
    }

    /**
     * Logs the stack trace of the {@link Throwable} {@code e} at the {@link Level#WARN WARN} level.
     */
    default void warn(Throwable e) {
        logMessage(Level.WARN, e, null);
    }

    /**
     * Logs a message at the {@link Level#WARN WARN} level including the stack trace of the given {@link Throwable}.
     */
    default void warn(Throwable e, CharSequence msg) {
        logMessage(Level.WARN, e, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#WARN WARN} level including the stack trace of the given {@link
     * Throwable}.
     */
    default void warn(Throwable e, CharSequence pattern, Object... args) {
        logMessage(Level.WARN, e, pattern, args);
    }

    /**
     * Logs a message at the {@link Level#ERROR ERROR} level.
     */
    default void error(CharSequence msg) {
        logMessage(Level.ERROR, null, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#ERROR ERROR} level.
     */
    default void error(CharSequence pattern, Object... args) {
        logMessage(Level.ERROR, null, pattern, args);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#ERROR ERROR} level.
     */
    default void error(Throwable e) {
        logMessage(Level.ERROR, e, null);
    }

    /**
     * Logs a message at the {@link Level#ERROR ERROR} level including the stack trace of the given {@link Throwable}.
     */
    default void error(Throwable throwable, CharSequence msg) {
        logMessage(Level.ERROR, throwable, msg);
    }

    /**
     * Logs a message with parameters at the {@link Level#ERROR ERROR} level including the stack trace of the given
     * {@link Throwable}.
     */
    default void error(Throwable e, CharSequence pattern, Object... args) {
        logMessage(Level.ERROR, e, pattern, args);
    }

    void logMessage(Level level, Throwable throwable, CharSequence pattern, Object... args);
}
