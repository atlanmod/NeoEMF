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

package fr.inria.atlanmod.neoemf.io.structure;

/**
 * The simple reprensetation of an identifier of a {@link Classifier}.
 */
public class Identifier {

    /**
     * The literal representation of this identifier.
     */
    private final String value;

    /**
     * Whether this identifier has been auto-generated.
     */
    private final boolean generated;

    /**
     * Constructs a new {@code Identifier} with its {@code value}.
     *
     * @param value the literal representation of this identifier
     * @param generated {@code true} if this identifier has been auto-generated
     *
     * @see #original(String)
     * @see #generated(String)
     */
    private Identifier(String value, boolean generated) {
        this.value = value;
        this.generated = generated;
    }

    /**
     * Creates a new {@code Identifier} which has a non-generated {@code value}, for example: a read value.
     *
     * @param value the literal representation of this identifier
     *
     * @return a new identifier
     */
    public static Identifier original(String value) {
        return new Identifier(value, false);
    }

    /**
     * Creates a new {@code Identifier} which has a generated {@code value}, for example: a value processed from another
     * value.
     *
     * @param value the literal representation of this identifier
     *
     * @return a new identifier
     */
    public static Identifier generated(String value) {
        return new Identifier(value, true);
    }

    /**
     * Returns the literal representation of this identifier.
     *
     * @return the literal representation of this identifier
     */
    public String getValue() {
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
    public String toString() {
        return value;
    }
}
