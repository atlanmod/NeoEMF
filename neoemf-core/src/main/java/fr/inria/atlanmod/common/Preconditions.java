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

package fr.inria.atlanmod.common;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Static convenience methods that help a method or constructor check whether it was invoked correctly (whether its
 * <i>preconditions</i> have been met). These methods generally accept a {@code boolean} expression which is expected to
 * be {@code true} (or in the case of {@code checkNotNull}, an object reference which is expected to be non-null). When
 * {@code false} (or {@code null}) is passed instead, the {@code Preconditions} method throws an unchecked exception,
 * which helps the calling method communicate to <i>its</i> caller that <i>that</i> caller has made a mistake.
 * <p>
 * <b>Warning:</b> The goal of this class is to improve readability of code, but in some circumstances this may come at
 * a significant performance cost. Remember that parameter values for message construction must all be computed eagerly,
 * and autoboxing and varargs array creation may happen as well, even when the precondition check then succeeds (as it
 * should almost always do in production). In some circumstances these wasted CPU cycles and allocations can add up to a
 * real problem.
 */
@ParametersAreNonnullByDefault
public final class Preconditions {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Preconditions() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the check fails
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @param pattern    a template for the exception message should the check fail
     * @param args       the arguments to be substituted into the message template
     *
     * @throws IllegalArgumentException if {@code expression} is false
     * @throws NullPointerException     if the check fails and either {@code pattern} or {@code args} is null
     */
    public static void checkArgument(boolean expression, String pattern, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(format(pattern, args));
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
     * to the calling method.
     *
     * @param expression a boolean expression
     *
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
     * to the calling method.
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the check fails
     *
     * @throws IllegalStateException if {@code expression} is false
     */
    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance, but not involving any parameters
     * to the calling method.
     *
     * @param expression a boolean expression
     * @param pattern    a template for the exception message should the check fail
     * @param args       the arguments to be substituted into the message template
     *
     * @throws IllegalStateException if {@code expression} is false
     * @throws NullPointerException  if the check fails and either {@code pattern} or {@code args} is null
     */
    public static void checkState(boolean expression, String pattern, Object... args) {
        if (!expression) {
            throw new IllegalStateException(format(pattern, args));
        }
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     *
     * @return the non-null reference that was validated
     *
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(@Nullable T reference) {
        if (null == reference) {
            throw new NullPointerException();
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param message   the exception message to use if the check fails
     *
     * @return the non-null reference that was validated
     *
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(@Nullable T reference, String message) {
        if (null == reference) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param pattern   a template for the exception message should the check fail
     * @param args      the arguments to be substituted into the message template. Arguments are converted to strings
     *                  using {@link String#valueOf(Object)}.
     *
     * @return the non-null reference that was validated
     *
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(@Nullable T reference, String pattern, Object... args) {
        if (null == reference) {
            throw new NullPointerException(format(pattern, args));
        }
        return reference;
    }

    /**
     * Ensures that {@code index} specifies a valid <i>element</i> in an array, list or string of {@code size}. An
     * element index may range from zero, inclusive, to {@code size}, exclusive.
     *
     * @param index a user-supplied index identifying an element of an array, list or string
     * @param size  the size of that array, list or string
     *
     * @return the value of {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is not less than {@code size}
     * @throws IllegalArgumentException  if {@code size} is negative
     */
    public static int checkElementIndex(int index, int size) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(format("index (%d) must not be negative", index));
        }
        else if (size < 0) {
            throw new IllegalArgumentException(format("negative size: %d", size));
        }
        else if (index >= size) {
            throw new IndexOutOfBoundsException(format("index (%d) must be less than size (%d)", index, size));
        }
        return index;
    }

    /**
     * Ensures that {@code index} specifies a valid <i>position</i> in an array, list or string of {@code size}. A
     * position index may range from zero to {@code size}, inclusive.
     *
     * @param index a user-supplied index identifying a position in an array, list or string
     * @param size  the size of that array, list or string
     *
     * @return the value of {@code index}
     *
     * @throws IndexOutOfBoundsException if {@code index} is negative or is greater than {@code size}
     * @throws IllegalArgumentException  if {@code size} is negative
     */
    public static int checkPositionIndex(int index, int size) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(format("index (%d) must not be negative", index));
        }
        else if (size < 0) {
            throw new IllegalArgumentException(format("negative size: %d", size));
        }
        else if (index > size) {
            throw new IndexOutOfBoundsException(format("index (%d) must not be greater than size (%d)", index, size));
        }
        return index;
    }

    /**
     * Returns a formatted string using the specified format string and arguments.
     *
     * @param pattern a format string
     * @param args    the arguments referenced by the format specifiers in the format string
     *
     * @return a formatted string
     */
    private static String format(String pattern, Object... args) {
        return String.format(pattern, args);
    }
}
