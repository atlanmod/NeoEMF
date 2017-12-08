/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A type of redirection for {@link DataMapperTester}.
 */
@ParametersAreNonnullByDefault
enum RedirectionType {
    ATTRIBUTE("Attribute"),
    REFERENCE("Reference");

    /**
     * The name of this redirection.
     */
    @Nonnull
    private final String label;

    /**
     * Constructs a new {@code RedirectionType} with the given {@code label}.
     *
     * @param label the name of this redirection
     */
    RedirectionType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "With " + label;
    }
}
