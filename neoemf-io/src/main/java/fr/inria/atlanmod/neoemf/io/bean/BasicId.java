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

package fr.inria.atlanmod.neoemf.io.bean;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The simple representation of an identifier of a {@link BasicElement}.
 */
@ParametersAreNonnullByDefault
public class BasicId {

    /**
     * The literal representation of this identifier.
     */
    private final String value;

    /**
     * Whether this identifier has been auto-generated.
     */
    private final boolean generated;

    /**
     * Constructs a new {@code BasicId} with its {@code value}.
     *
     * @param value     the literal representation of this identifier
     * @param generated {@code true} if this identifier has been auto-generated
     *
     * @see #original(String)
     * @see #generated(String)
     */
    private BasicId(String value, boolean generated) {
        this.value = value;
        this.generated = generated;
    }

    /**
     * Creates a new {@code BasicId} which has a non-generated {@code value}, for example: a read value.
     *
     * @param value the literal representation of this identifier
     *
     * @return a new identifier
     */
    public static BasicId original(String value) {
        return new BasicId(value, false);
    }

    /**
     * Creates a new {@code BasicId} which has a generated {@code value}, for example: a value processed from
     * another value.
     *
     * @param value the literal representation of this identifier
     *
     * @return a new identifier
     */
    public static BasicId generated(String value) {
        return new BasicId(value, true);
    }

    /**
     * Returns the literal representation of this identifier.
     *
     * @return the literal representation of this identifier
     */
    public String value() {
        return value;
    }

    /**
     * Returns whether this identifier has been auto-generated.
     *
     * @return {@code true} if this identifier has been auto-generated
     */
    public boolean isGenerated() {
        return generated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!BasicId.class.isInstance(o)) {
            return false;
        }

        BasicId basicId = BasicId.class.cast(o);
        return Objects.equals(value, basicId.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
