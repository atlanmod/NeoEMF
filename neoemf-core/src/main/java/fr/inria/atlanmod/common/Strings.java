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

import fr.inria.atlanmod.common.annotation.Static;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * Static utility methods related to {@link String}.
 */
@Static
@ParametersAreNonnullByDefault
public final class Strings {

    /**
     * The empty string {@code ""}.
     */
    public static final String EMPTY = "";

    /**
     * A string for a space character.
     */
    public static final String SPACE = " ";

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Strings() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns {@code true} if the given string is null or is the empty string.
     *
     * @param s a string reference to check
     *
     * @return {@code true} if the string is {@code null} or is the empty string
     */
    public static boolean isNullOrEmpty(@Nullable String s) {
        return isNull(s) || s.isEmpty();
    }

    /**
     * Returns the given string if it is non-{@code null}; the empty string otherwise.
     *
     * @param s the string to test and possibly return
     *
     * @return {@code s} itself if it is non-null; {@link #EMPTY} if it is {@code null}
     */
    @Nonnull
    public static String nullToEmpty(@Nullable String s) {
        return isNull(s) ? EMPTY : s;
    }

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     *
     * @param s the string to test and possibly return
     *
     * @return {@code s} itself if it is nonempty; {@code null} if it is empty or {@code null}
     */
    @CheckForNull
    public static String emptyToNull(@Nullable String s) {
        return isNullOrEmpty(s) ? null : s;
    }
}
