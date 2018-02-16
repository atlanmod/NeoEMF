/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.hash.Hasher;
import fr.inria.atlanmod.commons.hash.StandardHashers;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link IdProvider}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractIdProvider implements IdProvider {

    /**
     * The hasher used to generate {@link Id} from string.
     */
    @Nonnull
    private final Hasher hasher = StandardHashers.XX;

    /**
     * Constructs a new {@code AbstractIdProvider}.
     */
    protected AbstractIdProvider() {
    }

    /**
     * Returns the hasher used to generate {@code Id} instances from a value.
     *
     * @return the hasher
     */
    @Nonnull
    protected Hasher hasher() {
        return hasher;
    }
}
