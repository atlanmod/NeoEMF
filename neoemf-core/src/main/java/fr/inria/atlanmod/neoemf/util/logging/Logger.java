/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.util.logging;

import java.text.MessageFormat;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNullableByDefault;

/**
 * An object that provides logging operations, filtered by {@link Level}s.
 */
@ParametersAreNullableByDefault
public interface Logger {

    /**
     * The name of the root {@link Logger}.
     */
    String ROOT_LOGGER_NAME = "";

    /**
     * Logs a message at the {@link Level#TRACE TRACE} level.
     *
     * @param message the message to log
     */
    default void trace(CharSequence message) {
        log(Level.TRACE, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#TRACE TRACE} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void trace(CharSequence message, Object... params) {
        log(Level.TRACE, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#TRACE TRACE} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void trace(Throwable e) {
        log(Level.TRACE, e);
    }

    /**
     * Logs a message at the {@link Level#TRACE TRACE} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void trace(Throwable e, CharSequence message) {
        log(Level.TRACE, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#TRACE TRACE} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void trace(Throwable e, CharSequence message, Object... params) {
        log(Level.TRACE, e, message, params);
    }

    /**
     * Logs a message at the {@link Level#DEBUG DEBUG} level.
     *
     * @param message the message to log
     */
    default void debug(CharSequence message) {
        log(Level.DEBUG, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#DEBUG DEBUG} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void debug(CharSequence message, Object... params) {
        log(Level.DEBUG, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#DEBUG DEBUG} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void debug(Throwable e) {
        log(Level.DEBUG, e);
    }

    /**
     * Logs a message at the {@link Level#DEBUG DEBUG} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void debug(Throwable e, CharSequence message) {
        log(Level.DEBUG, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#DEBUG DEBUG} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void debug(Throwable e, CharSequence message, Object... params) {
        log(Level.DEBUG, e, message, params);
    }

    /**
     * Logs a message at the {@link Level#INFO INFO} level.
     *
     * @param message the message to log
     */
    default void info(CharSequence message) {
        log(Level.INFO, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#INFO INFO} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void info(CharSequence message, Object... params) {
        log(Level.INFO, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#INFO INFO} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void info(Throwable e) {
        log(Level.INFO, e);
    }

    /**
     * Logs a message at the {@link Level#INFO INFO} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void info(Throwable e, CharSequence message) {
        log(Level.INFO, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#INFO INFO} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void info(Throwable e, CharSequence message, Object... params) {
        log(Level.INFO, e, message, params);
    }

    /**
     * Logs a message at the {@link Level#WARN WARN} level.
     *
     * @param message the message to log
     */
    default void warn(CharSequence message) {
        log(Level.WARN, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#WARN WARN} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void warn(CharSequence message, Object... params) {
        log(Level.WARN, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#WARN WARN} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void warn(Throwable e) {
        log(Level.WARN, e);
    }

    /**
     * Logs a message at the {@link Level#WARN WARN} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void warn(Throwable e, CharSequence message) {
        log(Level.WARN, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#WARN WARN} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void warn(Throwable e, CharSequence message, Object... params) {
        log(Level.WARN, e, message, params);
    }

    /**
     * Logs a message at the {@link Level#ERROR ERROR} level.
     *
     * @param message the message to log
     */
    default void error(CharSequence message) {
        log(Level.ERROR, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#ERROR ERROR} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void error(CharSequence message, Object... params) {
        log(Level.ERROR, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#ERROR ERROR} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void error(Throwable e) {
        log(Level.ERROR, e);
    }

    /**
     * Logs a message at the {@link Level#ERROR ERROR} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void error(Throwable e, CharSequence message) {
        log(Level.ERROR, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#ERROR ERROR} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void error(Throwable e, CharSequence message, Object... params) {
        log(Level.ERROR, e, message, params);
    }

    /**
     * Logs a message at the {@link Level#FATAL FATAL} level.
     *
     * @param message the message to log
     */
    default void fatal(CharSequence message) {
        log(Level.FATAL, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#FATAL FATAL} level.
     *
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void fatal(CharSequence message, Object... params) {
        log(Level.FATAL, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the {@link Level#FATAL FATAL} level.
     *
     * @param e the exception to log, including its stack trace
     */
    default void fatal(Throwable e) {
        log(Level.FATAL, e);
    }

    /**
     * Logs a message at the {@link Level#FATAL FATAL} level including the stack trace of the given {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void fatal(Throwable e, CharSequence message) {
        log(Level.FATAL, e, message);
    }

    /**
     * Logs a message with parameters at the {@link Level#FATAL FATAL} level including the stack trace of the given
     * {@link Throwable}.
     *
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void fatal(Throwable e, CharSequence message, Object... params) {
        log(Level.FATAL, e, message, params);
    }

    /**
     * Logs a message at the given {@code level}.
     *
     * @param level   the logging level
     * @param message the message to log
     */
    default void log(@Nonnull Level level, CharSequence message) {
        log(level, null, message, new Object[0]);
    }

    /**
     * Logs a message with parameters at the given {@code level}.
     *
     * @param level   the logging level
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    default void log(@Nonnull Level level, CharSequence message, Object... params) {
        log(level, null, message, params);
    }

    /**
     * Logs the stack trace of the given {@link Throwable} at the given {@code level}.
     *
     * @param level the logging level
     * @param e     the exception to log, including its stack trace
     */
    default void log(@Nonnull Level level, Throwable e) {
        log(level, e, null, new Object[0]);
    }

    /**
     * Logs a message at the given {@code level} including the stack trace of the given {@link Throwable}.
     *
     * @param level   the logging level
     * @param e       the exception to log, including its stack trace
     * @param message the message to log
     */
    default void log(@Nonnull Level level, Throwable e, CharSequence message) {
        log(level, e, message, new Object[0]);
    }

    /**
     * Logs a message with parameters at the given {@code level} including the stack trace of the given {@link
     * Throwable}.
     *
     * @param level   the logging level
     * @param e       the exception to log, including its stack trace
     * @param message the message to log; the format depends on the {@link MessageFormat}
     * @param params  parameters to the message
     */
    void log(@Nonnull Level level, Throwable e, CharSequence message, Object... params);
}
