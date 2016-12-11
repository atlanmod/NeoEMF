package fr.inria.atlanmod.neoemf.logging;

import org.apache.logging.log4j.Level;

public interface Logger {

    default void trace(String msg) {
        logMessage(Level.TRACE, null, msg);
    }

    default void trace(String pattern, Object... args) {
        logMessage(Level.TRACE, null, pattern, args);
    }

    default void debug(String msg) {
        logMessage(Level.DEBUG, null, msg);
    }

    default void debug(String pattern, Object... args) {
        logMessage(Level.DEBUG, null, pattern, args);
    }

    default void info(String msg) {
        logMessage(Level.INFO, null, msg);
    }

    default void info(String pattern, Object... args) {
        logMessage(Level.INFO, null, pattern, args);
    }

    default void warn(String msg) {
        logMessage(Level.WARN, null, msg);
    }

    default void warn(String pattern, Object... args) {
        logMessage(Level.WARN, null, pattern, args);
    }

    default void warn(Throwable e) {
        logMessage(Level.WARN, e, null);
    }

    default void warn(Throwable e, String msg) {
        logMessage(Level.WARN, e, msg);
    }

    default void warn(Throwable e, String pattern, Object... args) {
        logMessage(Level.WARN, e, pattern, args);
    }

    default void error(String msg) {
        logMessage(Level.ERROR, null, msg);
    }

    default void error(String pattern, Object... args) {
        logMessage(Level.ERROR, null, pattern, args);
    }

    default void error(Throwable e) {
        logMessage(Level.ERROR, e, null);
    }

    default void error(Throwable e, String msg) {
        logMessage(Level.ERROR, e, msg);
    }

    default void error(Throwable e, String pattern, Object... args) {
        logMessage(Level.ERROR, e, pattern, args);
    }

    void logMessage(Level level, Throwable throwable, String pattern, Object... args);
}
