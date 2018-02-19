/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import fr.inria.atlanmod.neoemf.data.Backend;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Information about a {@link Backend}.
 */
@ParametersAreNonnullByDefault
public class BackendReport {

    /**
     * The unique identifier of the backend.
     */
    @Nonnull
    private final String name;

    /**
     * The type of the backend.
     */
    @Nonnull
    private final Class<? extends Backend> type;

    /**
     * Constructs a new {@code BackendReport} for the specified {@code backend}.
     *
     * @param backend the related backend
     */
    public BackendReport(Backend backend) {
        this.name = backend.toString();
        this.type = backend.getClass();
    }

    /**
     * Returns the unique identifier of the backend.
     *
     * @return the unique identifier
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Returns the type of the backend.
     *
     * @return the type
     */
    @Nonnull
    public Class<? extends Backend> type() {
        return type;
    }
}
